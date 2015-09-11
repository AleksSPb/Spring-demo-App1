package com.springapp.mvc.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import liquibase.Liquibase;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class H2DataSource {

    // jdbc:h2:mem:testdb
    @Bean
    public DataSource dataSource() {

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).build();
        return db;

    }

  
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server startDBManager() throws SQLException {
        return Server.createWebServer();
    }

}