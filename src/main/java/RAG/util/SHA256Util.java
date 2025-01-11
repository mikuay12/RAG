package RAG.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHA256Util {
    public static String sha256(String input) {
        try {
            // 获取SHA-256算法的MessageDigest对象
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // 计算哈希值，将输入数据转化为字节数组，进行哈希计算
            byte[] hash = md.digest(input.getBytes());

            // 将哈希值转化为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                // 0xff 用于将字节转为无符号整数
                String hex = Integer.toHexString(0xff & b);
                // 如果是单个字符，补充0
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            // 返回十六进制表示的哈希值
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // 捕获异常并抛出运行时异常
            throw new RuntimeException(e);
        }
    }
}
