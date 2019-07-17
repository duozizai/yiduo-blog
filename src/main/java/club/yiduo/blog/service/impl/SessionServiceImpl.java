package club.yiduo.blog.service.impl;

import club.yiduo.blog.domain.User;
import club.yiduo.blog.exception.PasswordException;
import club.yiduo.blog.form.UserForm;
import club.yiduo.blog.service.SessionService;
import club.yiduo.blog.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public User signup(UserForm userForm) {
        if (!StringUtils.equals(userForm.getPassword(), userForm.getPasswordConfirm())) {
            throw new PasswordException("密码不一致");
        }
        User user = userForm.toUser();
        userService.saveOrUpdate(user);
        return user;
    }
}
