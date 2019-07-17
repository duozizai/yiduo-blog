package club.yiduo.blog.controller;


import club.yiduo.blog.domain.Blog;
import club.yiduo.blog.service.BlogService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class BlogController extends AbstractCrudController<BlogService, Blog> {

    protected BlogController(BlogService service) {
        super(service);
    }
}
