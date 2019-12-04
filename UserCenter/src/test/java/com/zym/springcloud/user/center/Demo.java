package com.zym.springcloud.user.center;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.RuntimeMXBean;

public class Demo {


    public static void main(String[] args) {

        for (MemoryPoolMXBean memoryPoolMXBean : ManagementFactory.getMemoryPoolMXBeans()) {
            System.out.println(memoryPoolMXBean.getName());
            System.out.println(memoryPoolMXBean.getType());
            System.out.println(memoryPoolMXBean.getUsage());
            System.out.println(memoryPoolMXBean.getCollectionUsage());

            System.out.println("---------------");
        }
        System.out.println("===============================");
        for (MemoryManagerMXBean memoryManagerMXBean : ManagementFactory.getMemoryManagerMXBeans()) {
            System.out.println(memoryManagerMXBean.getName());
        }

        System.out.println("===============================");
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getStartTime());
        System.out.println(runtimeMXBean.getInputArguments());
        System.out.println(runtimeMXBean.getVmVersion());


    }
}
