package RAG.controller;


import com.aa.pojo.Result;
import com.aa.pojo.User;
import com.aa.service.UserService;
import com.aa.util.TokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;



    @RequestMapping("/register")
    public Result register(String username, String password){
        User u = userService.findByUsername(username);
        if(u == null){
            //没有占用，就注册
            userService.register(username, password);
            return Result.success("注册成功",null);
        }
        else
            return Result.error("该用户名已存在!");
    }

    @RequestMapping("/login")
    public Result login(String username, String password, HttpServletResponse res, HttpSession session){
        User u = userService.login(username, password);
        if(u == null)
            return Result.error("登录失败，请检查账号或密码是否正确");

        String token = tokenUtil.generateToken(u);

        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(7*24*60*60);
        cookie.setPath("/");
        res.addCookie(cookie);
        return Result.success("登录成功",token);
    }


    @RequestMapping("/editinfo")
    public Result editUserInfo(String username, String user_name, String user_age, String user_sex, String email){
        User u =userService.findByUsername(username);
        if(u == null){
            return Result.error("操作失败，该用户不存在");
        }
        userService.editUserInfo(username,user_name,user_age,user_sex,email);
        return Result.success("修改成功",null);
    }

    @RequestMapping("/resetPassword")
    public Result resetPassword(String username, String password,String newpassword,String repassword) {
        User u = userService.findByUsername(username);
        if (u == null)
            return Result.error("操作失败，该用户不存在");
        if (newpassword.equals(repassword))
            return Result.error("新密码与确认密码不一致");
        if (u.getPassword().equals(password)) {
            System.out.println(u.getPassword().equals(password));

            userService.resetPassword(username, newpassword);
            return Result.success("修改成功", null);
        } else {
            System.out.println(u.getPassword().equals(password));
            return Result.error("原密码错误");
        }

    }
}
