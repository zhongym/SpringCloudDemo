package com.zym.springcloud.user.center.orika;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.OrikaSystemProperties;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Yuanmao.Zhong
 */
public class OrikaDemo {
    public static void main(String[] args) {
        BigDecimal multiply = new BigDecimal("10.002").multiply(BigDecimal.valueOf(10));


        System.setProperty(OrikaSystemProperties.WRITE_SOURCE_FILES, "true");
        System.setProperty(OrikaSystemProperties.WRITE_SOURCE_FILES_TO_PATH, "D:\\OtherProjects\\SpringCloudDemo\\UserCenter\\src\\main\\java\\com\\zym\\springcloud\\user\\center\\orika");

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        mapperFactory.classMap(DTO1.class,DTO2.class)
                .field("i","i")
                .byDefault()
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        DTO1 dto1 = new DTO1();
        dto1.setString("str1");
        dto1.setDateTime(LocalDateTime.now());
        dto1.setDate(new Date());
        dto1.setDto(dto1);

        DTO2 dto2 = mapper.map(dto1, DTO2.class);
        System.out.println(dto2);



    }
}
