package club.yiduo.blog;

import club.yiduo.blog.resolver.ConditionalSearchArgumentResolver;
import club.yiduo.blog.resolver.PageableArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
public class YiduoBlogApplication  extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(YiduoBlogApplication.class, args);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new PageableArgumentResolver());
        argumentResolvers.add(new ConditionalSearchArgumentResolver());
    }
}
