package club.yiduo.blog.resolver;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 条件查询谓词
 *
 * @author jy
 */
@SuppressWarnings("unchecked")
public enum Operator {

    EQ {
        @Override
        public void accept(QueryWrapper qw, ConditionParam param) {
            qw.eq(param.getField(), param.getValues()[0]);
        }
    },
    NE {
        @Override
        public void accept(QueryWrapper qw, ConditionParam param) {
            qw.ne(param.getField(), param.getValues()[0]);
        }
    },
    GT {
        @Override
        public void accept(QueryWrapper qw, ConditionParam param) {
            qw.gt(param.getField(), param.getValues()[0]);
        }
    },
    LT {
        @Override
        public void accept(QueryWrapper qw, ConditionParam param) {
            qw.lt(param.getField(), param.getValues()[0]);
        }
    },
    LE {
        @Override
        public void accept(QueryWrapper qw, ConditionParam param) {
            qw.le(param.getField(), param.getValues()[0]);
        }
    },
    BETWEEN {
        @Override
        public void accept(QueryWrapper qw, ConditionParam param) {
            qw.between(param.getField(), param.getValues()[0], param.getValues()[1]);
        }
    },
    START_WITH {
        @Override
        public void accept(QueryWrapper qw, ConditionParam param) {
            qw.likeRight(param.getField(), param.getValues()[0]);
        }
    },
    LIKE {
        @Override
        public void accept(QueryWrapper qw, ConditionParam param) {
            qw.like(param.getField(), param.getValues()[0]);
        }
    },
    IN {
        @Override
        public void accept(QueryWrapper qw, ConditionParam param) {
            qw.in(param.getField(), param.getValues());
        }
    };

    public abstract void accept(QueryWrapper qw, ConditionParam param);

}
