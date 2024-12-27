package RAG.mapper;
import com.aa.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from users where username=#{username}")
    User findByUsername(String username);

    @Insert("insert into users(username,password) values(#{username},#{password})")
    void register(String username, String password);

    @Select("select * from users where username=#{username} and password=#{password}")
    User login(String username, String password);

//    @Update("update users_data set user_power=#{power} where username=#{username}")
//    void setPower(String username,String power);

    @Update("update users set name=#{user_name},age=#{user_age},sex=#{user_sex},email=#{email} where username=#{username}")
    void editUserInfo(String username, String user_name, String user_age, String user_sex, String email);

    @Update("update users set password=#{password} where username=#{username}")
    void resetPassword(String username,String password);

    @Delete("delete from users where username=#{username}")
    void deleteUser(String username);
}
