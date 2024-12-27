package RAG.service.impl;

import com.aa.mapper.UserMapper;
import com.aa.pojo.User;
import com.aa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        userMapper.register(username,password);
    }

    @Override
    public User login(String username, String password) {
        return userMapper.login(username,password);
    }

    @Override
    public void editUserInfo(String username, String name, String age, String sex, String email) {
        userMapper.editUserInfo(username,name,age,sex,email);
    }

    @Override
    public void resetPassword(String username, String password) {
        userMapper.resetPassword(username,password);
    }

    @Override
    public void deleteUser(String username) {
        userMapper.deleteUser(username);
    }
}
