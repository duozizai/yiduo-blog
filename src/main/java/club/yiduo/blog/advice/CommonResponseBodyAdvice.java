package club.yiduo.blog.advice;

import club.yiduo.blog.controller.BlogController;
import club.yiduo.blog.controller.UserController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

//@RestControllerAdvice(assignableTypes = {UserController.class, BlogController.class})
@RestControllerAdvice
public class CommonResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof IPage) {
            return new IPageAdapter((IPage) body);
        }
        return body;
    }

    /**
     * 分页信息 封装响应
     */
    @Data
    public static class IPageAdapter {

        private Long pageNumber;

        private Long pageSize;

        private Long total;

        private Long totalPages;

        private List rows;

        public IPageAdapter(IPage page) {
            this.totalPages = page.getPages();
            this.total = page.getTotal();
            this.pageNumber = page.getCurrent();
            this.pageSize = page.getSize();
            this.rows = page.getRecords();
        }

    }
}