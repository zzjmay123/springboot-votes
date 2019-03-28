package com.zzjmay.spi.loader;

import com.zzjmay.spi.annotation.ZSPI;
import com.zzjmay.spi.util.Holder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * ZSPI的加载器
 * Created by zzjmay on 2019/3/27.
 */
public class ZzjExtensionClassLoader<T> {

    /**
     * 定义SPI文件扫描路径
     */
    private static final String ZZJMAY_DIRECTORY = "META-INF/zzjmay/";


    /**
     * 分割SPI上默认拓展点字符串用的
     */
    private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");

    /**
     * 扩展点加载器缓存
     * key是类对象， value是自意义的加载器
     */
    private static final Map<Class<?>, ZzjExtensionClassLoader<?>> EXTENSION_LOADERS
            = new ConcurrentHashMap<>();

    /**
     * 拓展点缓存
     */
    private static final Map<Class<?>, Object> EXTENSION_INSTANCES = new ConcurrentHashMap<>();

    /**
     * 接口的class
     */
    private final Class<?> type;


    /**
     * 接口SPI默认的实现
     */
    private String cacheDefaultName;


    /**
     * 异常记录
     */
    private Map<String, IllegalStateException> exceptions = new ConcurrentHashMap<String, IllegalStateException>();


    private final Holder<Map<String, Class<?>>> cachedClasses = new Holder<Map<String, Class<?>>>();

    private final Map<String, Holder<Object>> cachedInstances = new ConcurrentHashMap<String, Holder<Object>>();

    private ZzjExtensionClassLoader(Class<T> type) {
        this.type = type;
    }

    /**
     * 获取默认拓展点
     * @return
     */
    private static ClassLoader findClassLoader(){
        return ZzjExtensionClassLoader.class.getClassLoader();
    }

    private static <T> boolean withExtensionAnnotation(Class<T> type) {
        return type.isAnnotationPresent(ZSPI.class);
    }

    public static <T> ZzjExtensionClassLoader<T> getExtensionLoader(Class<T> type) {
        if (type == null)//1.拓展点类型非空判断
            throw new IllegalArgumentException("Extension type == null");
        if (!type.isInterface()) {//2.拓展点类型只能是接口
            throw new IllegalArgumentException("Extension type(" + type + ") is not interface!");
        }
        if (!withExtensionAnnotation(type)) {//3.需要添加spi注解,否则抛异常
            throw new IllegalArgumentException("Extension type(" + type +
                    ") is not extension, because WITHOUT @" + ZSPI.class.getSimpleName() + " Annotation!");
        }
        //从缓存EXTENSION_LOADERS中获取,如果不存在则新建后加入缓存
        //对于每一个拓展,都会有且只有一个ExtensionLoader与其对应
        ZzjExtensionClassLoader<T> loader = (ZzjExtensionClassLoader<T>) EXTENSION_LOADERS.get(type);
        if (loader == null) {
            EXTENSION_LOADERS.putIfAbsent(type, new ZzjExtensionClassLoader<T>(type));
            loader = (ZzjExtensionClassLoader<T>) EXTENSION_LOADERS.get(type);
        }
        return loader;
    }


    /**
     * 获取默认扩展点的类
     * @return
     */
    public T getDefaultExtension() {
        getExtensionClasses();
        if (null == cacheDefaultName || cacheDefaultName.length() == 0
                || "true".equals(cacheDefaultName)) {
            return null;
        }
        return getExtension(cacheDefaultName);
    }

    /**
     * 按名称获取对应class实现
     * @param name
     * @return
     */
    private T getExtension(String name) {

        if(name == null || name.length() == 0){
            throw new IllegalArgumentException("Extension name == null");
        }

        if("true".equals(name)){
            return getDefaultExtension();
        }

        Holder<Object> holder = cachedInstances.get(name);
        if(holder == null){
            cachedInstances.putIfAbsent(name,new Holder<>());
            holder  = cachedInstances.get(name);
        }
        Object instance = holder.getValue();

        if(instance == null){
            synchronized (holder){
                instance = holder.getValue();
                if(instance == null){
                    instance = createExtension(name);
                    holder.setValue(instance);
                }
            }
        }

        return (T)instance;
    }

    /**
     * 实例化class对象
     * @param name
     * @return
     */
    private Object createExtension(String name) {

        Class<?> clazz = getExtensionClasses().get(name);

        if(clazz == null){
            //
            throw new IllegalArgumentException("");

        }
        try{
            T instance = (T) EXTENSION_INSTANCES.get(clazz);

            if(instance == null) {
                //反射生成对象
                EXTENSION_INSTANCES.putIfAbsent(clazz,(T)clazz.newInstance());
                instance = (T) EXTENSION_INSTANCES.get(clazz);
            }

            return  instance;

        }catch (Exception e){
            throw new IllegalStateException("Extension instance(name: " + name + ", class: " +
                    type + ")  could not be instantiated: " + e.getMessage(), e);
        }
    }

    /**
     * 获取扩展点class 并缓存
     * @return
     */
    private Map<String,Class<?>> getExtensionClasses(){

        Map<String,Class<?>> classes = cachedClasses.getValue();

        if(classes == null){
            synchronized (cachedClasses){
                classes = cachedClasses.getValue();
                if(classes == null){
                    classes = loadExtensionClasses();
                    cachedClasses.setValue(classes);
                }
            }
        }

        return  classes;

    }


    /**
     * 1. 设置接口默认的实现类名
     * 2. 加载文件
     * @return
     */
    private Map<String, Class<?>> loadExtensionClasses() {
        final ZSPI defaultAnnotation = type.getAnnotation(ZSPI.class);
        //获取默认的值
        if(defaultAnnotation != null){
            String value = defaultAnnotation.value();

            if(value != null && (value = value.trim()).length()>0){
                String[] names = NAME_SEPARATOR.split(value);

                if(names.length > 1){
                    throw new IllegalStateException("more than 1 default extension name on extension " + type.getName()
                            + ": " + Arrays.toString(names));
                }

                if(names.length == 1){
                    cacheDefaultName = names[0];
                }
            }
        }
        Map<String,Class<?>> extensionClasses = new HashMap<>();
        //加载文件
        loadFile(extensionClasses,ZZJMAY_DIRECTORY);
        return extensionClasses;
    }

    /**
     * 加载配置文件中的class
     * @param extensionClasses
     * @param dir
     */
    private void loadFile(Map<String, Class<?>> extensionClasses, String dir) {
        String fileName = dir + type.getName();
        try {
            Enumeration<URL> urls;
            ClassLoader classLoader = findClassLoader();
            if (classLoader != null) {
                urls = classLoader.getResources(fileName);
            } else {
                urls = ClassLoader.getSystemResources(fileName);
            }
            if (urls != null) {
                while (urls.hasMoreElements()) {
                    java.net.URL url = urls.nextElement();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
                        try {
                            String line = null;
                            while ((line = reader.readLine()) != null) {
                                final int ci = line.indexOf('#');
                                if (ci >= 0) {
                                    line = line.substring(0, ci);
                                }
                                line = line.trim();
                                if (line.length() > 0) {
                                    try {
                                        String name = null;
                                        int i = line.indexOf('=');
                                        if (i > 0) {
                                            name = line.substring(0, i).trim();
                                            line = line.substring(i + 1).trim();
                                        }
                                        if (line.length() > 0) {
                                            Class<?> clazz = Class.forName(line, true, classLoader);
                                            if (!type.isAssignableFrom(clazz)) {
                                                throw new IllegalStateException("Error when load extension class(interface: " +
                                                        type + ", class line: " + clazz.getName() + "), class "
                                                        + clazz.getName() + "is not subtype of interface.");
                                            }
                                            extensionClasses.put(name, clazz);//加入缓存
                                        }//源码中还有其他的判断,这个版本暂不实现
                                    } catch (Throwable t) {
                                        IllegalStateException e = new IllegalStateException("Failed to load extension class(interface: " + type + ", class line: " + line + ") in " + url + ", cause: " + t.getMessage(), t);
                                        exceptions.put(line, e);
                                    }
                                }
                            } // end of while read lines
                        } finally {
                            reader.close();
                        }
                    } catch (Throwable t) {
                        //logger.error("Exception when load extension class(interface: " +
                        //        type + ", class file: " + url + ") in " + url, t);
                    }
                } // end of while urls
            }
        } catch (Throwable e) {
            //logger.error("Exception when load extension class(interface: " + type + ", description file: " + fileName + ").", e);
        }
    }


}
