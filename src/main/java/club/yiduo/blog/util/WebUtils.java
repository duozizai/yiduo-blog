package club.yiduo.blog.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

/**
 * Utils - Web
 */
public final class WebUtils {

    public static Map<String, Object> errorPackage(BindingResult bindingResult) {
        Map<String, Object> message = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("key", error.getField());
            errorMap.put("message", error.getDefaultMessage());
            list.add(errorMap);
        }
        message.put("errors", list);
        return message;
    }

}
