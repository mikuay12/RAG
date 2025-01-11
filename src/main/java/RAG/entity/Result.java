package RAG.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code = 0;
    private String message = "";
    private T data = null;


    public static Result success(String message){
        return new Result<>(0,message,null);
    }
    public static <E> Result<E> success(String message,E data){
        return new Result<>(0,message,data);
    }
    public static <E> Result<E> success(E data){
        return new Result<>(0,"响应成功",data);
    }

    public static Result error(String message){
        return new Result(1,message,null);
    }

    public static <E> Result<E> error(String message,E data){
        return new Result(1,message,data);
    }



}