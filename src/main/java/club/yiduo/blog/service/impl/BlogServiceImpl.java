package club.yiduo.blog.service.impl;

import club.yiduo.blog.domain.Blog;
import club.yiduo.blog.mapper.BlogMapper;
import club.yiduo.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanwen
 * @since 2019-07-12
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
