package RAG.util;

import RAG.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Rin
 */
@Component
public class TokenUtil {


    public static String generateToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create()
                .withAudience(user.getId().toString())
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.none());
        return token;
    }


    // 用于验证 token 是否有效
    public static Boolean verify(String token) {
        try {
            // 获取 token 的过期时间
            Date expirationTime = JWT.decode(token).getExpiresAt();

            // 验证 token 是否过期
            return !expirationTime.before(new Date()); // token 已过期

            // 如果没有过期，返回 true
        } catch (Exception e) {
            // 如果解码过程中发生异常，表示 token 无效
            return false;
        }
    }

    // 用于从 token 中提取用户 ID
    public static Integer getUserId(String token) {
        try {
            // 从 token 中提取用户 ID
            String userIdStr = JWT.decode(token).getAudience().get(0);

            // 将 userId 从字符串转换为整数
            return Integer.parseInt(userIdStr);
        } catch (Exception e) {
            // 如果解析过程中发生异常，表示 token 无效或用户 ID 无效
            return null;
        }
    }


}
