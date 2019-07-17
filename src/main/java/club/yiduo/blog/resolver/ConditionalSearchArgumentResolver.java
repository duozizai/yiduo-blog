package club.yiduo.blog.resolver;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;

public class ConditionalSearchArgumentResolver implements HandlerMethodArgumentResolver {


    private static final String PARAM_FILTERING = "filtering";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Wrapper.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Wrapper resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
                                   NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws UnsupportedEncodingException {

        String parameter = webRequest.getParameter(PARAM_FILTERING);
        if (StringUtils.isEmpty(parameter)) {
            return Condition.create();
        }
        Filtering filtering = methodParameter.getParameterAnnotation(Filtering.class);
        if (filtering == null) {
            return ConditionParam.toQueryWrapper(JSONArray.parseArray(URLDecoder.decode(parameter, "UTF-8"), ConditionParam.class));
        }

        // 条件分组组名
        String group = filtering.value();
        return ConditionParam.toQueryWrapper(JSONArray.parseArray(URLDecoder.decode(parameter, "UTF-8"), ConditionParam.class)
            .stream()
            .filter(p -> group.equals(p.getGroup()))
            .collect(Collectors.toList()));

    }
}
