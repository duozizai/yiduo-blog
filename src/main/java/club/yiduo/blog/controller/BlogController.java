package club.yiduo.blog.controller;


import club.yiduo.blog.domain.Blog;
import club.yiduo.blog.service.BlogService;
import club.yiduo.blog.service.impl.BlogServiceImpl;
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
public class BlogController extends AbstractCrudController<BlogServiceImpl, Blog> {

    protected BlogController(BlogServiceImpl service) {
        super(service);
    }
}
