package cn.qingfeng.core;


import cn.qingfeng.model.ISensitiveTypeStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 *策略类的工厂
 * @author ldf
 */
public class DesensitizationFactory {

    private DesensitizationFactory() {
    }

    /**
     * 这里采用一个 Map 集合 对指定的策略类进行缓存 避免对象的重复创建
     */
    private static final Map<Class<?>, ISensitiveTypeStrategy> MAP = new HashMap<>();

    
    @SuppressWarnings("all")
    public static ISensitiveTypeStrategy getDesensitization(Class<?> clazz) {
        //  如果传递的只是接口 不是实现类 则抛出异常
        if (clazz.isInterface()) {
            throw new UnsupportedOperationException("desensitization is interface, what is expected is an implementation class !");
        }
        return MAP.computeIfAbsent(clazz, k -> {
            try {
                //  返回指定 Class 的策略类 同时缓存在 Map 中
                return (ISensitiveTypeStrategy) clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new UnsupportedOperationException(e.getMessage(), e);
            }
        });
    }

}
