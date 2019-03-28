package com.zzjmay.spi;

import com.zzjmay.spi.loader.ZzjExtensionClassLoader;
import com.zzjmay.spi.log.Log;

/**
 * Created by zzjmay on 2019/3/28.
 */
public class ZSPITest {

    public static void main(String[] args) {
        Log defaultExtention = ZzjExtensionClassLoader.getExtensionLoader(Log.class).getDefaultExtension();

        //DUBBO SPI 中根据URL的总线机制，实现动态机制Adaptve，动态生成字节码，来完成getExtension(name)的操作
        defaultExtention.say();
    }
}
