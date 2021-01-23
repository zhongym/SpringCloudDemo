package com.zym.springcloud.user.center.springBeanCopier;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.*;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.util.ClassUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yuanmao.Zhong
 */
public class MBeanUtils {

    private static final ConcurrentHashMap<Key, BeanCopier> MAP;
    private static final WebConversionService SERVICE;
    private static final org.springframework.cglib.core.Converter CONVERT;

    static {
        MAP = new ConcurrentHashMap<>();
        SERVICE = new WebConversionService("yyyy-MM-dd HH:mm:ss");
//                .dateFormat("yyyy-MM-dd")
//                .dateTimeFormat("yyyy-MM-dd HH:mm:ss")
//                .timeFormat("HH:mm:ss"));
        //Date 格式化
//        SERVICE.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
        // Date <-> LocalDateTime
        JSR310DateConverters.addDateConverters(SERVICE);
        //复杂对象处理
        SERVICE.addConverter(new ConditionalGenericConverter() {
            @Override
            public java.util.Set<ConvertiblePair> getConvertibleTypes() {
                return Collections.singleton(new ConvertiblePair(Object.class, Object.class));
            }

            @Override
            public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
//                ConverAnnotation annotation = targetType.getAnnotation(ConverAnnotation.class);
//                boolean useConverter = annotation != null && annotation.value();
                return MBeanUtils.copy(source, targetType.getObjectType(), true);
            }

            @Override
            public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
                return !ClassUtils.isPrimitiveOrWrapper(sourceType.getObjectType())
                        && !ClassUtils.isPrimitiveOrWrapper(targetType.getObjectType());
            }
        });

        CONVERT = new org.springframework.cglib.core.Converter() {
            @Override
            public Object convert(Object sourceFieldValue, Class aClass, Object fieldName) {
                if (sourceFieldValue == null) {
                    return null;
                }
                if (sourceFieldValue.getClass() == aClass) {
                    return sourceFieldValue;
                }
                try {
                    return SERVICE.convert(sourceFieldValue, aClass);
                } catch (ConverterNotFoundException e) {
                    return null;
                }
            }
        };
    }

    public static <T> T copy(Object source, T target, boolean useConverter) {
        BeanCopier beanCopier = MAP.computeIfAbsent(new Key(source.getClass(), target.getClass(), useConverter), (k) -> {
            return BeanCopier.create(source.getClass(), target.getClass(), useConverter);
        });
        beanCopier.copy(source, target, useConverter ? CONVERT : null);
        return target;
    }

    public static <T> T copy(Object source, T target) {
        return copy(source, target, false);
    }

    private static <T> T copy(Object source, Class<T> target, boolean useConverter) {
        T t = BeanUtils.instantiateClass(target);
        return copy(source, t, useConverter);
    }


    @Data
    @AllArgsConstructor
    private static class Key {
        private Class<?> sourceClazz;
        private Class<?> targetClazz;
        private boolean useConverter;
    }


    public static class JSR310DateConverters {

        public static void addDateConverters(ConverterRegistry converterRegistry) {
            converterRegistry.addConverter(LocalDateToDateConverter.INSTANCE);
            converterRegistry.addConverter(DateToLocalDateConverter.INSTANCE);
            converterRegistry.addConverter(ZonedDateTimeToDateConverter.INSTANCE);
            converterRegistry.addConverter(DateToZonedDateTimeConverter.INSTANCE);
            converterRegistry.addConverter(LocalDateTimeToDateConverter.INSTANCE);
            converterRegistry.addConverter(DateToLocalDateTimeConverter.INSTANCE);
        }

        private JSR310DateConverters() {
        }

        public static class LocalDateToDateConverter implements Converter<LocalDate, Date> {

            public static final LocalDateToDateConverter INSTANCE = new LocalDateToDateConverter();

            private LocalDateToDateConverter() {
            }

            @Override
            public Date convert(LocalDate source) {
                return Date.from(source.atStartOfDay(ZoneId.systemDefault()).toInstant());
            }
        }

        public static class DateToLocalDateConverter implements Converter<Date, LocalDate> {
            public static final DateToLocalDateConverter INSTANCE = new DateToLocalDateConverter();

            private DateToLocalDateConverter() {
            }

            @Override
            public LocalDate convert(Date source) {
                return ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault()).toLocalDate();
            }
        }

        public static class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
            public static final ZonedDateTimeToDateConverter INSTANCE = new ZonedDateTimeToDateConverter();

            private ZonedDateTimeToDateConverter() {
            }

            @Override
            public Date convert(ZonedDateTime source) {
                return Date.from(source.toInstant());
            }
        }

        public static class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
            public static final DateToZonedDateTimeConverter INSTANCE = new DateToZonedDateTimeConverter();

            private DateToZonedDateTimeConverter() {
            }

            @Override
            public ZonedDateTime convert(Date source) {
                return ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
            }
        }

        public static class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {
            public static final LocalDateTimeToDateConverter INSTANCE = new LocalDateTimeToDateConverter();

            private LocalDateTimeToDateConverter() {
            }

            @Override
            public Date convert(LocalDateTime source) {
                return Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
            }
        }

        public static class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {
            public static final DateToLocalDateTimeConverter INSTANCE = new DateToLocalDateTimeConverter();

            private DateToLocalDateTimeConverter() {
            }

            @Override
            public LocalDateTime convert(Date source) {
                return LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
            }
        }
    }
}
