package com;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.pwz.myGenerator.Log;
import com.pwz.util.ExceptionUtil;
import com.pwz.util.SpringUtil;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by pwz on 2017/11/13.
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan("com.pwz.*.dao")
@ServletComponentScan
public class Study {

    private static Log log = new Log("Study");

    //DataSource配置
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new com.alibaba.druid.pool.DruidDataSource();
    }

    //提供SqlSeesion
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        Interceptor[] plugins =  new Interceptor[]{ pageHelper()};

        sqlSessionFactoryBean.setPlugins(plugins);

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    //配置mybatis的分页插件pageHelper
    @Bean
    public PageInterceptor pageHelper(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
//        properties.setProperty("dialect","com.mysql.jdbc.Driver");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(Study.class,args);
        try {
        } catch (Exception e){
            e.printStackTrace();
            log.info(ExceptionUtil.getContent(e));
            throw e;
        }finally {
            log.write2Path();
        }

    }
}
