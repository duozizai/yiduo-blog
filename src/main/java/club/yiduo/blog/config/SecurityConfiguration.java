package club.yiduo.blog.config;

import club.yiduo.blog.auth.AuthenticationService;
import club.yiduo.blog.auth.CustomAuthenticationFilter;
import club.yiduo.blog.auth.MyAuthenticationProvider;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    private final SecurityExceptionHandler securityExceptionHandler;

    private final SecurityLogoutHandler securityLogoutHandler;

    private final SecurityAuthenticationSuccessHandler securityAuthenticationSuccessHandler;

    private final MyAuthenticationProvider myAuthenticationProvider;

    @Autowired
    public SecurityConfiguration(AuthenticationService authenticationService, SecurityExceptionHandler securityExceptionHandler, SecurityLogoutHandler securityLogoutHandler, MyAuthenticationProvider myAuthenticationProvider,SecurityAuthenticationSuccessHandler securityAuthenticationSuccessHandler) {
        this.authenticationService = authenticationService;
        this.securityExceptionHandler = securityExceptionHandler;
        this.securityLogoutHandler = securityLogoutHandler;
        this.myAuthenticationProvider = myAuthenticationProvider;
        this.securityAuthenticationSuccessHandler = securityAuthenticationSuccessHandler;
    }
//    @Autowired
//    public SecurityConfiguration(AuthenticationService authenticationService, SecurityExceptionHandler securityExceptionHandler, SecurityLogoutHandler securityLogoutHandler,SecurityAuthenticationSuccessHandler securityAuthenticationSuccessHandler) {
//        this.authenticationService = authenticationService;
//        this.securityExceptionHandler = securityExceptionHandler;
//        this.securityLogoutHandler = securityLogoutHandler;
//        this.securityAuthenticationSuccessHandler =securityAuthenticationSuccessHandler;
//    }

    @Bean
    public HeaderHttpSessionIdResolver headerHttpSessionIdResolver() {
        return new HeaderHttpSessionIdResolver("Authorization");
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider)
                .userDetailsService(authenticationService)
                .passwordEncoder(new RawPasswordEncoder());
//        auth
//                .userDetailsService(authenticationService)
//                .passwordEncoder(new RawPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers(HttpMethod.OPTIONS);
//        .antMatchers(HttpMethod.POST,"/auth/singup");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers("/auth/signup", "/auth/login").permitAll()
                .antMatchers("/**").authenticated()
            .and()
                .formLogin()
                .loginProcessingUrl("/auth/login")
                .usernameParameter("email").passwordParameter("password")
                .successHandler(securityAuthenticationSuccessHandler)
                .failureHandler(securityExceptionHandler)
                .permitAll()

            .and()
                .logout()
                .logoutUrl("/auth/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessHandler(securityLogoutHandler)
                .permitAll()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(securityExceptionHandler)
                .accessDeniedHandler(securityExceptionHandler)
            .and()
                .headers()
                .frameOptions()
                .disable()
            .and()
                .csrf()
                .disable();
        http.addFilterAt(getAuthenticationFilter(),
            UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 存储明文密码
     */
    private static class RawPasswordEncoder implements PasswordEncoder {

        @Override
        public String encode(CharSequence rawPassword) {
            return rawPassword.toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return rawPassword != null && Objects.equals(rawPassword.toString(), encodedPassword);
        }
    }

    @Component
    public static class SecurityExceptionHandler implements AccessDeniedHandler, AuthenticationFailureHandler, AuthenticationEntryPoint {

        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
            sendError(response, HttpStatus.FORBIDDEN, accessDeniedException);
        }

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            JSONObject result = new JSONObject();
            result.put("status", false);
            result.put("code", HttpStatus.UNAUTHORIZED.value());
            result.put("message", "用户名或密码错误");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(result.toJSONString());
        }

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            JSONObject result = new JSONObject();
            result.put("status", false);
            result.put("code", HttpStatus.UNAUTHORIZED.value());
            result.put("message", "未登录系统，暂无权限访问");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(result.toJSONString());
        }

        private void sendError(HttpServletResponse response, HttpStatus status, RuntimeException ex) throws IOException {
            response.sendError(status.value(), ex.getMessage());
        }
    }

    @Component
    public static class SecurityLogoutHandler implements LogoutSuccessHandler {


        @Override
        public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            JSONObject result = new JSONObject();
            result.put("status", true);
            result.put("message", "logout");
            httpServletResponse.getWriter().write(result.toJSONString());
        }
    }

    /**
     * 该filter 与 formLogin()
     *                         .loginProcessingUrl("/auth/login")
     *                         .usernameParameter("email").passwordParameter("password")
     *                         .successHandler(securityAuthenticationSuccessHandler)
     *                         .failureHandler(securityExceptionHandler)
     *                         冲突  .loginProcessingUrl("/auth/login")
     * @return
     * @throws Exception
     */
    CustomAuthenticationFilter getAuthenticationFilter() throws Exception{
        CustomAuthenticationFilter myUsernamePasswordAuthenticationFilte = new CustomAuthenticationFilter();
        myUsernamePasswordAuthenticationFilte.setFilterProcessesUrl("/auth/login");//必须写 不然与formLogin().loginProcessingUrl("/auth/login") 冲突
        myUsernamePasswordAuthenticationFilte.setAuthenticationSuccessHandler(securityAuthenticationSuccessHandler);
        myUsernamePasswordAuthenticationFilte.setAuthenticationFailureHandler(securityExceptionHandler);
        myUsernamePasswordAuthenticationFilte.setAuthenticationManager(authenticationManagerBean());
        return myUsernamePasswordAuthenticationFilte;
    }

    @Component
    public static class SecurityAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            JSONObject result = new JSONObject();
            result.put("status", true);
            Map<String, String> token = new HashMap<>();
            token.put("token", request.getSession().getId());
            result.put("data", token);
            response.getWriter().write(result.toJSONString());
        }
    }
}
