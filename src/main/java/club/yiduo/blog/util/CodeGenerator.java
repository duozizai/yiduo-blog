package club.yiduo.blog.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * Mybatis 生成器
 *
 * @author jy
 */
public class CodeGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java/")
                .setAuthor("yanwen")
                .setOpen(false)
                .setEnableCache(false)
                .setIdType(IdType.AUTO)
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setControllerName("%sController")
                .setDateType(DateType.TIME_PACK)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setFileOverride(true);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.POSTGRE_SQL);
        dsc.setUrl("jdbc:postgresql://localhost:5432/blog");
        dsc.setSchemaName("public");
        dsc.setDriverName("org.postgresql.Driver");
        dsc.setUsername("yanwen");
        dsc.setPassword("0416")
                .setTypeConvert((globalConfig, fieldType) -> {
                    String t = fieldType.toLowerCase();
                    if (t.contains("char") || t.contains("text")) {
                        return DbColumnType.STRING;
                    } else if (t.contains("bigint")) {
                        return DbColumnType.LONG;
                    } else if (t.contains("int")) {
                        return DbColumnType.INTEGER;
                    } else if (t.contains("date") || t.contains("year")) {
                        return DbColumnType.DATE;
                    } else if (t.contains("text")) {
                        return DbColumnType.STRING;
                    } else if (t.contains("timestamp")) {
                        return DbColumnType.LOCAL_DATE_TIME;
                    } else if (t.contains("bit")) {
                        return DbColumnType.BOOLEAN;
                    } else if (t.contains("decimal")) {
                        return DbColumnType.BIG_DECIMAL;
                    } else if (t.contains("clob")) {
                        return DbColumnType.CLOB;
                    } else if (t.contains("blob")) {
                        return DbColumnType.BYTE_ARRAY;
                    } else if (t.contains("float")) {
                        return DbColumnType.FLOAT;
                    } else if (t.contains("double")) {
                        return DbColumnType.DOUBLE;
                    } else if (t.contains("json") || t.contains("enum")) {
                        return DbColumnType.STRING;
                    } else if (t.contains("boolean")) {
                        return DbColumnType.BOOLEAN;
                    }
                    return DbColumnType.STRING;
                });
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(scanner("表名"));
        strategy.setExclude(scanner("model名"));
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
//        strategy.setExclude("flyway_schema_history");
        strategy.setControllerMappingHyphenStyle(true)
                .setEntityBooleanColumnRemoveIsPrefix(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("club.yiduo.blog")
                .setController("controller")
                .setMapper("mapper")
                .setEntity("domain")
                .setXml("mappers");
        mpg.setPackageInfo(pc);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
