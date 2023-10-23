package com.pickCom.common.config;

import com.pickCom.common.resolver.CustomMapArgumentResolver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.List;

@Configuration
// MyBatis의 매퍼 인터페이스를 찾아서 빈으로 등록합니다.
@MapperScan("com.pickCom")
public class MyBatisConfig implements WebMvcConfigurer {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // 마이바티스 매퍼 파일 위치 설정
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/**/*_SQL.xml");
        factoryBean.setMapperLocations(resources);

        return factoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public CustomMapArgumentResolver customMapArgumentResolver() {
        return new CustomMapArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(customMapArgumentResolver());
    }
}
