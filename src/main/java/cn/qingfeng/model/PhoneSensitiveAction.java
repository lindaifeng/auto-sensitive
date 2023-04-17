package cn.qingfeng.model;


/** 电话号码脱敏
 * @Author： 清峰
 * @Description： May there be no bug in the world！
 */
public class PhoneSensitiveAction implements ISensitiveTypeStrategy {


    @Override
    public String sensitiveData(String origin) {
        return this.sensitiveData(origin, 3, 4, "*");
    }
}
