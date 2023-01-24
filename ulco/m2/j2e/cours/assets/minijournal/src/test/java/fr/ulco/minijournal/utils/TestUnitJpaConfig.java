package fr.ulco.minijournal.utils;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@TestConfiguration
@EnableJpaRepositories(basePackages = TestUnitJpaConfig.BASE_PACKAGE_DAO)
@TestPropertySource("classpath:application-test.properties")
@EnableTransactionManagement
public class TestUnitJpaConfig {

    static final String BASE_PACKAGE_DAO = "fr.ulco.minijournal.model.dao";

    static final String BASE_PACKAGE_ENTITIES = "fr.ulco.minijournal.model.entities";
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setName("testDb")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(BASE_PACKAGE_ENTITIES);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}