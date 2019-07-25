package com.liuceng.hat.web.controller.arg;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.CaseFormat;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Controller 分页参数注入
 *
 * @author jy
 */
public class PageableArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String PARAM_PAGE = "pageNumber";
    private static final String PARAM_PAGE_SIZE = "pageSize";
    private static final String PARAM_SORT = "sort";
    private static final String PARAM_DIRECTION = "order";

    private static final String DEFAULT_PAGE = "1";
    private static final String DEFAULT_PAGE_SIZE = "20";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return IPage.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public IPage resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
                                 NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        Page page = getPageFromRequest(webRequest);

        String sortParameter = webRequest.getParameter(PARAM_SORT);
        if (StringUtils.isEmpty(sortParameter)) {
            return page;
        }

        String[] sorts = Stream.of(sortParameter)
            .flatMap(s -> Arrays.stream(s.split(",")))
            .map(s -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s))
            .toArray(String[]::new);

        Direction.valueOf(
            Optional.ofNullable(webRequest.getParameter(PARAM_DIRECTION)).orElse(Direction.desc.name()))
            .setSortProperties(page, sorts);
        return page;
    }

    private Page getPageFromRequest(NativeWebRequest webRequest) {
        String pageString = Optional.ofNullable(webRequest.getParameter(PARAM_PAGE)).orElse(DEFAULT_PAGE);
        String pageSizeString = Optional.ofNullable(webRequest.getParameter(PARAM_PAGE_SIZE)).orElse(DEFAULT_PAGE_SIZE);

        return new Page(Integer.valueOf(pageString), Integer.valueOf(pageSizeString));
    }


    private enum Direction {
        asc {
            @Override
            public void setSortProperties(Page page, String[] sort) {
                page.setAsc(sort);
            }
        }, desc {
            @Override
            public void setSortProperties(Page page, String[] sort) {
                page.setDesc(sort);
            }
        };

        public abstract void setSortProperties(Page page, String[] sort);

    }
}
