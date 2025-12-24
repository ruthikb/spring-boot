package com.xworkz.ecomerce.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableJpaRepositories(
        basePackages = "com.xworkz.ecomerce.h2.repository",
        entityManagerFactoryRef = "h2EntityManagerFactory",
        transactionManagerRef = "h2TransactionManager"
)
public class  H2Configuration {

        @Bean(name = "h2DataSource")
        @ConfigurationProperties(prefix = "spring.datasource.h2")
        public DataSource h2DataSource() {
            return DataSourceBuilder.create().build();
        }



    @Bean(name = "h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(
            @Qualifier("h2DataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.xworkz.ecomerce.h2.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(props);

        em.setPersistenceUnitName("h2PU");
        return em;
    }
        @Bean(name = "h2TransactionManager")
        public PlatformTransactionManager h2TransactionManager(
                @Qualifier("h2EntityManagerFactory") EntityManagerFactory emf) {
            return new JpaTransactionManager(emf);
        }
}


