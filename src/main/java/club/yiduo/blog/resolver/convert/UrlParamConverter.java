package club.yiduo.blog.resolver.convert;

import java.time.LocalDateTime;

/**
 * @author jy
 */
public interface UrlParamConverter<T> {

    T convert(String raw);

    class LocalDateTimeConverter implements UrlParamConverter<LocalDateTime> {

        @Override
        public LocalDateTime convert(String raw) {
            return LocalDateTime.parse(raw);
        }
    }
}

