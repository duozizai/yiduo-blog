package club.yiduo.blog.domain;

import club.yiduo.blog.domain.enumerations.UserStatus;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author yanwen
 * @since 2019-07-12
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "public.users")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String email;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 联系人电话
     */
    private String phone;

    private String password;

    /**
     * 账户状态
     */
    private UserStatus status;

    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("");

//        log.info("[原始用户角色列表装填]: ", roleList);
//        StringBuilder roles = new StringBuilder();
//
//        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(roles.substring(0, roles.length() - 1));
//        log.info("[遍历并返回用户的角色列表]: {}", authorityList);
//        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
