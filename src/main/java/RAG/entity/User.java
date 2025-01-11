package RAG.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@TableName("users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer role_id;
    private String username;
    @JsonIgnore
    private String password;
    private String name;
    private String email;
    private String img_url;
}
