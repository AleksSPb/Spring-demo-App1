package com.springapp.mvc.config;

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

//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.springapp.mvc")
@EnableJpaRepositories("com.springapp.mvc.repository")
public class JpaConfig implements DisposableBean {

    private EmbeddedDatabase emDb;

    @Bean(name = "h2InMemory")
    public EmbeddedDatabase h2InMemory() {

        if (this.emDb == null) {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            this.emDb = builder.setType(EmbeddedDatabaseType.H2).build();
        }
        return this.emDb;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(h2InMemory());
        entityManagerFactoryBean.setPackagesToScan("com.springapp.mvc");
        entityManagerFactoryBean.setPersistenceUnitName("MyPU");
//		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
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
    /*	@PostConstruct
    public void startDBManager() {
        DatabaseManagerSwing.main(new String[]{"--url", "jdbc:h2:mem:testdb", "--user", "sa", "--password", ""});
    }*/

    @Override
    public void destroy() {
        if (this.emDb != null) {
            this.emDb.shutdown();

        }
    }


}
