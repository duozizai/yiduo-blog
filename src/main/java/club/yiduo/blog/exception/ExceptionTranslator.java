package club.yiduo.blog.exception;


import club.yiduo.blog.util.WebUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jy on 2018/04/02
 */
@Slf4j
@ControllerAdvice
public class ExceptionTranslator {

//    /**
//     * 添加验证全局异常配置
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(BindException.class)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public Object handleBindException(BindException e) {
//        return WebUtils.errorPackage(e.getBindingResult());
//    }

}
