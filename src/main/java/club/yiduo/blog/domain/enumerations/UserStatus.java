package club.yiduo.blog.domain.enumerations;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum UserStatus implements IEnum<Integer> {
    VALID(0), INVALID(1);
    private final int value;
    UserStatus(int value) {
        this.value = value;
    }
    @Override
    public Integer getValue() {
        return this.value;
    }
}
