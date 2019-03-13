package com.zzjmay.spi;

import java.util.ServiceLoader;

/**
 * Created by zzjmay on 2019/3/12.
 */
public class JavaSPITest {

    public static void main(String[] args) {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);

        System.out.println("Java SPI");

        serviceLoader.forEach(Robot::sayHello);
        //从运行结果可以看出，java 的SPI机制会加载META-INF下的所有扩展实现类.
        //性能角度上来说，对于不必要加载的实际上并没有这个必要
    }
}
