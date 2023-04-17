package cn.qingfeng.model;

/** 邮箱脱敏
 * @Author： 清峰
 * @Description： May there be no bug in the world！
 */
public class EmailSensitiveAction implements ISensitiveTypeStrategy {

    @Override
    public String sensitiveData(String origin) {
        return origin.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
    }
}
