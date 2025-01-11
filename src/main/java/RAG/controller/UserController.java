package RAG.controller;

import RAG.entity.Result;
import RAG.entity.User;
import RAG.service.UserService;
import RAG.util.SHA256Util;
import RAG.util.TokenUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Rin
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Result register(@RequestParam String username, @RequestParam String password, @RequestParam String re_password) {
        if (!password.equals(re_password)) {
            return Result.error("两次密码不一致");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 使用 queryWrapper 查询，返回满足条件的唯一一个 User 对象
        User user = userService.getOne(queryWrapper.eq("username", username));
        if (user != null) {
            return Result.error("用户名已存在");
        }
        user = new User();
        user.setUsername(username);
        user.setPassword(SHA256Util.sha256(password));
        user.setRole_id(1);
        boolean saved = userService.save(user);
        if (!saved) {
            return Result.error("注册失败");
        }
        return Result.success("注册成功");
    }

    @RequestMapping("/login")
    public Result<String> login(@RequestParam String username, @RequestParam String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 使用 queryWrapper 查询，返回满足条件的唯一一个 User 对象
        User user = userService.getOne(queryWrapper.eq("username", username));
        if (user == null) {
            return Result.error("用户不存在");
        }
        if (!user.getPassword().equals(SHA256Util.sha256(password))) {
            return Result.error("密码错误");
        }
        String token = TokenUtil.generateToken(user);
        // 登录成功，返回用户信息
        return Result.success("登录成功", token);

    }


    // 分页查询所有用户
    @RequestMapping("/page")
    public Result<Page<User>> getUsersByPage(
            @RequestParam(defaultValue = "1") int page, // 当前页，默认第 1 页
            @RequestParam(defaultValue = "10") int size // 每页大小，默认 10 条
    ) {
        Page<User> userPage = userService.page(new Page<>(page, size));
        return Result.success(userPage);
    }


    // 根据 ID 查询用户
    @RequestMapping("/getUserByID")
    public Result<User> getUserByID(@RequestParam Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return Result.error("未找到用户", null);
        }
        return Result.success(user);
    }
 
    @RequestMapping("/getUserByUsername")
    public Result<User> getUserBy(@RequestParam String username) {
        // 使用 QueryWrapper 根据用户名查询用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);  // 设置查询条件，按用户名查询

        User user = userService.getOne(queryWrapper);  // 获取满足条件的唯一用户
        if (user == null) {
            return Result.error("未找到用户", null);
        }
        return Result.success(user);  // 返回查询到的用户
    }


    // 修改用户
    @PutMapping
    public Result updateUser(@RequestBody User user) {
        boolean updated = userService.updateById(user);
        if (!updated) {
            return Result.error("Failed to update user");
        }
        return Result.success(updated);
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Long id) {
        boolean removed = userService.removeById(id);
        if (!removed) {
            return Result.error("Failed to delete user");
        }
        return Result.success(removed);
    }
}
