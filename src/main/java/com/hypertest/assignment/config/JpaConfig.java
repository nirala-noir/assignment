package com.hypertest.assignment.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "employeeEntityManagerFactory",
        transactionManagerRef = "employeeTransactionManager",
        basePackages = {
                "com.hypertest.assignment.repo"
        }
)
public class JpaConfig {
    @Bean(name = "employeeDataSource")
    @ConfigurationProperties(prefix = "spring.employee.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "employeeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("employeeDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.hypertest.assignment.dto")
                .persistenceUnit("Employee")
                .build();
    }

    @Bean(name = "employeeTransactionManager")
    public PlatformTransactionManager employeeTransactionManager(
            @Qualifier("employeeEntityManagerFactory") EntityManagerFactory employeeEntityManagerFactory) {
        return new JpaTransactionManager(employeeEntityManagerFactory);
    }

}
