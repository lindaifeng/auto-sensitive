package cn.qingfeng.model;

/**
 * 脱敏策越模型
 *
 * @Author： 清峰
 * @Description： May there be no bug in the world！
 */
public interface ISensitiveTypeStrategy {

    /**
     * 数据脱敏方法
     *
     * @param origin 原始数据
     * @return 脱敏数据
     */
    String sensitiveData(String origin);

    /**
     * 数据脱敏方法
     *
     * @param origin        原始数据
     * @param prefixHideLen 前缀
     * @param suffixHideLen 后缀
     * @param symbol        符号
     * @return 脱敏数据
     */
    default String sensitiveData(String origin, Integer prefixHideLen, Integer suffixHideLen, String symbol) {
        if (origin == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = origin.length(); i < n; i++) {
            if (i < prefixHideLen) {
                sb.append(origin.charAt(i));
                continue;
            }
            if (i > (n - suffixHideLen - 1)) {
                sb.append(origin.charAt(i));
                continue;
            }
            sb.append(symbol);
        }
        return sb.toString();
    }
}