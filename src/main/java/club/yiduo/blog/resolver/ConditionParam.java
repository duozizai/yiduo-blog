package club.yiduo.blog.resolver;

import club.yiduo.blog.resolver.convert.UrlParamConverter;
import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;

import java.util.*;

/**
 * @author jy
 */
@Data
@SuppressWarnings("unchecked")
public class ConditionParam {

    private static final Map<String, UrlParamConverter> CONVERTERS = new HashMap<>();

    static {
        Arrays.stream(UrlParamConverter.class.getDeclaredClasses())
            .filter(UrlParamConverter.class::isAssignableFrom)
            .forEach(c -> {
                try {
                    CONVERTERS.put(c.getSimpleName(), ((Class<UrlParamConverter>) c).newInstance());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
    }

    private String field;
    private Operator operator;
    private Object[] values;
    private String converter;
    private String group;

    public static <T> Wrapper<T> toQueryWrapper(List<ConditionParam> params) {
        QueryWrapper<T> queryWrapper = Condition.create();
        if (!params.isEmpty()) {
            params.stream()
                .filter(p -> p.values != null && p.values.length > 0)
                .peek(p -> {
                    if (p.converter != null) {
                        p.setValues(Arrays.stream(p.values)
                            .filter(Objects::nonNull)
                            .map(a -> CONVERTERS.get(p.converter).convert(a.toString()))
                            .toArray());
                    }
                })
                .forEach(p -> p.operator.accept(queryWrapper, p));
        }
        return queryWrapper;
    }

}
