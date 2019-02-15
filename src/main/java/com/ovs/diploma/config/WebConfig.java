package com.ovs.diploma.config;

import com.ovs.diploma.filter.CharacterEncodingFilter;
import com.ovs.diploma.security.config.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.ovs.diploma.*" })
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class WebConfig {

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder
                .scanPackages("com.ovs.diploma.web.model", "com.ovs.diploma.security.model" )
                .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        prop.put("hibernate.connection.useUnicode","true");
        prop.put("hibernate.connection.characterEncoding","UTF-8");
        return prop;
    }

    @Bean(name = "dataSource")
    public BasicDataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/USERS");
        ds.setUsername("postgres");
        ds.setPassword("159753");
        return ds;
    }

    @Bean
    public HibernateTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html; charset=UTF-8");
        return viewResolver;
    }

//    @Bean
//    public FilterRegistrationBean <CharacterEncodingFilter> filterRegistrationBean() {
//        FilterRegistrationBean< CharacterEncodingFilter > registrationBean = new FilterRegistrationBean();
//        CharacterEncodingFilter customURLFilter = new CharacterEncodingFilter();
//        registrationBean.setFilter(customURLFilter);
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(2); //set precedence
//        return registrationBean;
//    }


}
