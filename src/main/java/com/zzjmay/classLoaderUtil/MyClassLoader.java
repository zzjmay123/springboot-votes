package com.zzjmay.classLoaderUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.Set;

/**
 * Created by zzjmay on 2019/3/23.
 */
public class MyClassLoader extends ClassLoader {

    //用于读取.class的文件路径
    private String path;
    //用于标记这些name的类是有自身加载的
    private Set<String> useMyClassLoaderLoad;

    public MyClassLoader(String path, Set<String> useMyClassLoaderLoad) {
        this.path = path;
        this.useMyClassLoaderLoad = useMyClassLoaderLoad;
    }


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        //看本地是否已经加载过了
        Class<?> c = findLoadedClass(name);

        if (c == null && useMyClassLoaderLoad.contains(name)) {
            //正常自定义的类加载器都是重写这个方法，来达到自定义加载
            c = findClass(name);

            if (c != null) {
                return c;
            }
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //根据文件系统加载class文件，并返回Byte 数组

        byte[] classBytes = getClassBytes(name);
        //调用ClassLoader提供的方法，将二进制数组转化成Class类
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    /**
     * 实现从指定目录加载二进制class文件
     * @param name
     * @return
     */
    private byte[] getClassBytes(String name) {

        String classname = name.substring(name.lastIndexOf('.') + 1, name.length()) + ".class";

        try {
            FileInputStream fileInputStream = new FileInputStream(path + classname);
            byte[] buffer = new byte[1024];

            int length = 0;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            while ((length = fileInputStream.read(buffer)) > 0) {

                byteArrayOutputStream.write(buffer,0,length);

            }

            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return new byte[0];
    }
}
