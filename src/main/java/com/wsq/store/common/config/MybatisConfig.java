package com.wsq.store.common.config;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.wsq.store.common.mapper")
public class MybatisConfig {

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("supportMethodsArguments", "true");
        p.setProperty("returnPageInfo", "check");
        p.setProperty("params", "pageNum=page;pageSize=rows;orderBy=orderBy");

        pageHelper.setProperties(p);
        return pageHelper;
    }
}
