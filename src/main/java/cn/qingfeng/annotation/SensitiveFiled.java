package cn.qingfeng.annotation;

import cn.qingfeng.model.ISensitiveTypeStrategy;
import cn.qingfeng.core.SensitiveFiledSerialize;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author： 清峰
 * @Description： May there be no bug in the world！
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveFiledSerialize.class)
public @interface SensitiveFiled {

    /**
     * 脱敏实现类
     */
    Class<? extends ISensitiveTypeStrategy> using();
    /**
     * 脱敏前缀位数（自定义脱敏使用）
     */
    int prefixHideLen() default 0;
    /**
     * 脱敏后缀位数（自定义脱敏使用）
     */
    int suffixHideLen() default 0;
    /**
     * 脱敏符号（自定义脱敏使用）
     */
    String symbol() default "*";

}

