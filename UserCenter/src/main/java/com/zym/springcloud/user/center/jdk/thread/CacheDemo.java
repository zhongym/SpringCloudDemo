package com.zym.springcloud.user.center.jdk.thread;

import fr.ujm.tse.lt2c.satin.cache.size.CacheInfo;
import fr.ujm.tse.lt2c.satin.cache.size.CacheLevel;
import fr.ujm.tse.lt2c.satin.cache.size.CacheLevelInfo;
import fr.ujm.tse.lt2c.satin.cache.size.CacheType;

/**
 * Created by wangzhilong on 2016/11/10.
 */
public class CacheDemo {
    public static void main(String[] args) {
        CacheInfo info = CacheInfo.getInstance();
        CacheLevelInfo levelOneDataInfo = info.getCacheInformation(CacheLevel.L1, CacheType.DATA_CACHE) ;
        System.out.println(levelOneDataInfo);

        CacheLevelInfo levelInstrInfo = info.getCacheInformation(CacheLevel.L1,CacheType.INSTRUCTION_CACHE);
        System.out.println(levelInstrInfo);
    }
}