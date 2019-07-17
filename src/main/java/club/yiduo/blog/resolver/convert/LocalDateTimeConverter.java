package club.yiduo.blog.resolver.convert;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author jy
 */
public class LocalDateTimeConverter implements UrlParamConverter {

    @Override
    public LocalDateTime convert(String raw) {
        return Instant.parse(raw).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
