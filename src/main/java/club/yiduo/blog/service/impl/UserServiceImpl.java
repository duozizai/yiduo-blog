package club.yiduo.blog.service.impl;

import club.yiduo.blog.domain.User;
import club.yiduo.blog.mapper.UserMapper;
import club.yiduo.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanwen
 * @since 2019-07-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
