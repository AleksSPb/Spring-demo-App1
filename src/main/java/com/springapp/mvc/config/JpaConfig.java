package com.springapp.mvc.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import java.util.Properties;

@Configuration
@ComponentScan("com.springapp.mvc")
@EnableJpaRepositories("com.springapp.mvc.repository")
public class JpaConfig implements DisposableBean {

    private static final String DEFAULT_CLASSPATH_LOCATION = "classpath:liquibase/db.changelog.xml";

    @Bean(name = "h2InMemory")
    public EmbeddedDatabase h2InMemory() {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public SpringLiquibase springLiquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(h2InMemory());
        springLiquibase.setChangeLog(DEFAULT_CLASSPATH_LOCATION);
        return springLiquibase;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(h2InMemory());
        entityManagerFactoryBean.setPackagesToScan("com.springapp.mvc.model");
        entityManagerFactoryBean.setPersistenceUnitName("MyPU");
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(va);
        entityManagerFactoryBean.setJpaProperties(hibProperties());
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.id.new_generator_mappings", "false");
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Override
    public void destroy() {
        if (this.h2InMemory() != null) {
            this.h2InMemory().shutdown();

        }
    }

}
