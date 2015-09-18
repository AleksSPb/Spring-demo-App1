package com.springapp.mvc.config;


import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    private static final String DEFAULT_CLASSPATH_LOCATION = "classpath:liquibase/db.changelog-master.xml";
    @Autowired
    DataSource emDb;

    @Bean
    public SpringLiquibase springLiquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(emDb);
        springLiquibase.setChangeLog(DEFAULT_CLASSPATH_LOCATION);
        return springLiquibase;
    }

}