package RAG.service;

import com.aa.pojo.User;

public interface UserService {
    User findByUsername(String username);

    void register(String username, String password);

    User login(String username, String password);

    void editUserInfo(String username, String name, String age, String sex, String email);

    void resetPassword(String username, String password);

    void deleteUser(String username);
}