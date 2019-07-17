package club.yiduo.blog.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("club.yiduo.blog.mapper")
public class MybatisPlusConfig {

}
