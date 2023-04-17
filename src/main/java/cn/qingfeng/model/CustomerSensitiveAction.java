package cn.qingfeng.model;


/** 自定义脱敏
 * @Author： 清峰
 * @Description： May there be no bug in the world！
 */
public class CustomerSensitiveAction implements ISensitiveTypeStrategy {


    @Override
    public String sensitiveData(String origin) {
        return origin;
    }

}
