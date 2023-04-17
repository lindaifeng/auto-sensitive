package cn.qingfeng.core;

import cn.qingfeng.annotation.SensitiveFiled;
import cn.qingfeng.model.CustomerSensitiveAction;
import cn.qingfeng.model.ISensitiveTypeStrategy;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Objects;

/**
 * @Author： 清峰
 * @Description： May there be no bug in the world！
 */
public class SensitiveFiledSerialize extends JsonSerializer<String> implements ContextualSerializer {

    /**
     * 脱敏前缀位数
     */
    private int prefixHideLen;
    /**
     * 脱敏后缀位数
     */
    private int suffixHideLen;
    /**
     * 脱敏符号
     */
    private String symbol;

    /**
     * 脱敏策略实现对象
     */
    private ISensitiveTypeStrategy strategist;

    public SensitiveFiledSerialize() {
    }


    public SensitiveFiledSerialize(int prefixHideLen, int suffixHideLen, String symbol, ISensitiveTypeStrategy strategist) {
        this.prefixHideLen = prefixHideLen;
        this.suffixHideLen = suffixHideLen;
        this.symbol = symbol;
        this.strategist = strategist;
    }


    /**
     * 1、重写createContextual()
     * 扫描脱敏注解创建序列化对象并注入属性
     *
     * @param serializerProvider 序列化对象
     * @param beanProperty       bean属性对象
     * @return json序列化对象
     * @throws JsonMappingException 异常
     */
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                SensitiveFiled annotation = beanProperty.getAnnotation(SensitiveFiled.class);
                if (annotation == null) {
                    annotation = beanProperty.getContextAnnotation(SensitiveFiled.class);
                }
                if (annotation != null) {
                    Class<? extends ISensitiveTypeStrategy> aClass = annotation.using();
                    ISensitiveTypeStrategy strategist = DesensitizationFactory.getDesensitization(aClass);
                    return new SensitiveFiledSerialize(
                            annotation.prefixHideLen(),
                            annotation.suffixHideLen(),
                            annotation.symbol(),
                            strategist
                    );
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }

    /**
     * 2、重写serialize()
     * 根据不同的枚举类型实现对应的数据脱敏方法
     *
     * @param str                元数据
     * @param jsonGenerator      json生成器
     * @param serializerProvider 序列化对象
     * @throws IOException 异常
     */
    @Override
    public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        assert strategist != null;
        String sensitiveData;
        if (strategist instanceof CustomerSensitiveAction){
             sensitiveData = strategist.sensitiveData(str, prefixHideLen, suffixHideLen, symbol);
        }else {
             sensitiveData = strategist.sensitiveData(str);
        }
        jsonGenerator.writeString(sensitiveData);
    }

}
