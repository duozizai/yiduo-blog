package club.yiduo.blog.controller;


import club.yiduo.blog.domain.User;
import club.yiduo.blog.service.UserService;
import club.yiduo.blog.service.impl.UserServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/users",produces = {
        MediaType.APPLICATION_JSON_UTF8_VALUE
})
public class UserController  extends AbstractCrudController<UserServiceImpl, User> {

    protected UserController(UserServiceImpl service) {
        super(service);
    }
}
