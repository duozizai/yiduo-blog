package club.yiduo.blog.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {
    @NotBlank(message = "不能为空")
    private String email;
    @NotBlank(message = "不能为空")
    private String password;
}
