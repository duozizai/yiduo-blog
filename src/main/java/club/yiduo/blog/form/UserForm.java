package club.yiduo.blog.form;

import club.yiduo.blog.domain.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class UserForm {
    @NotBlank(message = "不能为空")
    private String username;
    @NotBlank(message = "不能为空")
    private String email;
    @NotBlank(message = "不能为空")
    private String password;
    @NotBlank(message = "不能为空")
    private String passwordConfirm;
    @NotBlank(message = "不能为空")
    private String phone;

    public User toUser(){
        User user = new User();
        user.setPhone(this.phone)
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now())
                .setEmail(this.email)
                .setPassword(this.password)
                .setUsername(this.username);
        return user;
    }
}
