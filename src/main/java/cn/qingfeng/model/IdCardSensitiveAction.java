package cn.qingfeng.model;


/** 身份证脱敏
 * @Author： 清峰
 * @Description： May there be no bug in the world！
 */
public class IdCardSensitiveAction implements ISensitiveTypeStrategy {

    @Override
    public String sensitiveData(String origin) {
        return origin.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1*****$2");
    }
}
