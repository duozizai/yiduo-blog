package club.yiduo.blog.resolver;

/**
 * 条件查询分组
 *
 * @author jy
 */

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Filtering {

    String value();
}
