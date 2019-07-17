package club.yiduo.blog.service;

import club.yiduo.blog.domain.User;
import club.yiduo.blog.form.UserForm;

public interface SessionService {
    public User signup(UserForm userForm);
}
