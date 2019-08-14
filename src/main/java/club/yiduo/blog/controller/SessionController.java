package club.yiduo.blog.controller;

import club.yiduo.blog.domain.User;
import club.yiduo.blog.form.UserForm;
import club.yiduo.blog.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "auth",produces = {
        MediaType.APPLICATION_JSON_UTF8_VALUE
})
public class SessionController{
    @Autowired
    private SessionService sessionService;
    @PostMapping("signup")
    public User signup(@RequestBody @Valid UserForm userForm){
        return sessionService.signup(userForm);
    }

    @GetMapping("me")
    public User userContent(@AuthenticationPrincipal User user){
        return user;
    }
}
