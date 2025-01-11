package RAG.mapper;

import RAG.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Rin
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
