package club.yiduo.blog.auth;

import club.yiduo.blog.domain.User;
import club.yiduo.blog.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 登录认证处理
 *
 * @author jy
 */
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        QueryWrapper<User> qw = new QueryWrapper<>();
        return Optional
            .ofNullable(userService.getOne(qw.eq("email", email)))
//            .filter(m -> m.getStatus() == AgentMemberStatus.VALID)
            .orElseThrow(() -> new UsernameNotFoundException("用户名" + email + "不存在!"));
    }
}
