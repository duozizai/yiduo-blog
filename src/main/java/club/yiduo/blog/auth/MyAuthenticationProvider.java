package club.yiduo.blog.auth;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 自定义权限查询 权限验证
 */
@Slf4j
@Service
public class MyAuthenticationProvider implements AuthenticationProvider {

  private AuthenticationService userDetailService;

  @Autowired
  public MyAuthenticationProvider(AuthenticationService userDetailService) {
    this.userDetailService = userDetailService;
  }

  /**
   * 用户信息查询验证
   * @param authentication
   * @return
   * @throws AuthenticationException
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
// http请求的账户密码
    String username = authentication.getName();
    String password = (String) authentication.getCredentials();

    // 数据库根据用户名查询
    UserDetails userDetails = userDetailService.loadUserByUsername(username);

    log.info("[http请求的账户密码]: {}/{}", username, password);
    log.info("[数据库查询的账户密码]：{} ", userDetails);

    if (userDetails == null) {
      throw new BadCredentialsException("用户名未找到");
    }
    if(StringUtils.equals(userDetails.getPassword(),password)){
      throw new BadCredentialsException("密码不正确");
    }

    Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
    log.info("[设置authorities] : {}", authorities);

    return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
//    return authentication;
  }

//

  @Override
  public boolean supports(Class<?> aClass) {

    return true;
  }
}
