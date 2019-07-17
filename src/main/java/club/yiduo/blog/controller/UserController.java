package club.yiduo.blog.controller;


import club.yiduo.blog.domain.User;
import club.yiduo.blog.service.UserService;
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
@RequestMapping("/users")
public class UserController  extends AbstractCrudController<UserService, User> {

    protected UserController(UserService service) {
        super(service);
    }

}
