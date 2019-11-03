package com.zym.springcloud.user.center.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.AbstractRequestAttributesScope;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zhongym
 */
@SuppressWarnings("all")
@Component
public class RequestScopeCache {

    private static final Object NULL_OBJECT = new Object();

    public static void put(Object key, Object value) {
        Map<Object, Object> requestCache = getCache();
        requestCache.put(key, value == null ? NULL_OBJECT : value);
    }

    public static Object get(Object key) {
        Map<Object, Object> requestCache = getCache();
        Object o = requestCache.get(key);
        if (o == NULL_OBJECT) {
            return null;
        }
        return o;
    }

    public static <T> T get(Object key, Class<T> t) {
        return (T) get(key);
    }

    public static <T> T get(Object key, Class<T> t, Callable<? extends T> callable) {
        ConcurrentMap<Object, Object> requestCache = getCache();
        Object o = requestCache.get(key);
        if (o == NULL_OBJECT) {
            return null;
        }
        if (o != null) {
            return (T) o;
        }

        o = getCache().computeIfAbsent(key, (k) -> {
            try {
                T call = callable.call();
                return call == null ? NULL_OBJECT : call;
            } catch (Exception e) {
                throw new RuntimeException("获取缓存回调失败", e);
            }
        });

        if (o == NULL_OBJECT) {
            return null;
        }
        return (T) o;
    }

    public static void remove(Object key) {
        ConcurrentMap<Object, Object> requestCache = getCache();
        requestCache.remove(key);
    }


    public static ConcurrentMap<Object, Object> getCache() {
        return (ConcurrentMap<Object, Object>) context.getBean("syncRequestScopeCache_");
    }


    private static ApplicationContext context;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RequestScopeCache.context = applicationContext;
    }


    @Configuration
    public static class MapConfig {

        /**
         * 注册自定义的SyncRequestScope
         *
         * @return
         */
        @Bean
        public CustomScopeConfigurer getCustomScopeConfigurer() {
            CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
            customScopeConfigurer.addScope(SyncRequestScope.REFERENCE_REQUEST, new SyncRequestScope());
            return customScopeConfigurer;
        }

        /**
         * 请求域的ConcurrentMap构造方法
         *
         * @return
         */
        @Bean
        @Scope(value = SyncRequestScope.REFERENCE_REQUEST)
        public ConcurrentMap<Object, Object> syncRequestScopeCache_() {
            ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
            System.out.println("---------------->new ConcurrentHashMap<>()");
            return map;
        }
    }

    /**
     * 和 RequestScope不同的就是加了个同步，用户当前请求多个线程的情况使用
     */
    public static class SyncRequestScope extends AbstractRequestAttributesScope {

        public static final String REFERENCE_REQUEST = "syncRequest";

        @Override
        protected int getScope() {
            return RequestAttributes.SCOPE_REQUEST;
        }

        @Override
        public String getConversationId() {
            return null;
        }

        @Override
        public Object get(String name, ObjectFactory<?> objectFactory) {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            //同一请求加锁（请求内使用线程池情况）
            synchronized (requestAttributes) {
                return super.get(name, objectFactory);
            }
        }

        @Override
        public Object remove(String name) {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            //同一请求加锁（请求内使用线程池情况）
            synchronized (requestAttributes) {
                return super.remove(name);
            }
        }
    }

}
