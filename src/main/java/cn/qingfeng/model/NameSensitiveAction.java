package cn.qingfeng.model;


/** 名称脱敏
 * @Author： 清峰
 * @Description： May there be no bug in the world！
 */
public class NameSensitiveAction implements ISensitiveTypeStrategy {


    @Override
    public String sensitiveData(String origin) {
       return this.sensitiveData(origin,1,0,"*");
    }
}
