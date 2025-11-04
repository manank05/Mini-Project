package com.example.ons.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.example.ons")
@EnableTransactionManagement
@PropertySource("classpath:application.properties") // optional
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        // For MySQL
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/ons_db?useSSL=false&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("password");
        return ds;

        // For H2 (quick test), return:
        // DriverManagerDataSource ds = new DriverManagerDataSource();
        // ds.setDriverClassName("org.h2.Driver");
        // ds.setUrl("jdbc:h2:mem:onsdb;DB_CLOSE_DELAY=-1;MODE=MySQL");
        // ds.setUsername("sa");
        // ds.setPassword("");
        // return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean lsf = new LocalSessionFactoryBean();
        lsf.setDataSource(dataSource);
        lsf.setPackagesToScan("com.example.ons.model");
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.hbm2ddl.auto", "update"); // change to validate/none in prod
        props.put("hibernate.show_sql", "true");
        lsf.setHibernateProperties(props);
        return lsf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager tm = new HibernateTransactionManager();
        tm.setSessionFactory(sessionFactory);
        return tm;
    }
}
