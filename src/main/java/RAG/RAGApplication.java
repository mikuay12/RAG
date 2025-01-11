package RAG;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("RAG.mapper")
public class RAGApplication {
    public static void main(String[] args) {
        SpringApplication.run(RAGApplication.class, args);
        System.out.println("项目启动成功");
    }
}