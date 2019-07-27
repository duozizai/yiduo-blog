package club.yiduo.blog.controller;


import club.yiduo.blog.domain.Blog;
import club.yiduo.blog.domain.User;
import club.yiduo.blog.service.BlogService;
import club.yiduo.blog.service.impl.BlogServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanwen
 * @since 2019-07-12
 */
@RestController
@RequestMapping("/blogs")
public class BlogController extends AbstractCrudController<BlogServiceImpl, Blog> {

    protected BlogController(BlogServiceImpl service) {
        super(service);
    }
    @PostMapping
    public void saveOrUpdate(@Valid @RequestBody Blog blog, @AuthenticationPrincipal User user) {
        blog.setUpdatedAt(LocalDateTime.now())
                .setCreatedAt(LocalDateTime.now())
                .setUserId(user.getId());
        service.saveOrUpdate(blog);
    }

}
