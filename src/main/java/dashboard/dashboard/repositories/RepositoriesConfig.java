package dashboard.dashboard.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dax on 10-Apr-17.
 */
@Configuration
@EnableJpaRepositories(basePackages = "dashboard.dashboard.repositories")
@EnableTransactionManagement
public class RepositoriesConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://horton.elephantsql.com:5432/dhagylfk");
//        dataSource.setUsername("dhagylfk");
//        dataSource.setPassword("Fh4qfTEeD0iiKelxcdpaSwK-kGhMk3fv");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/citizens");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("dashboard.dashboard.model");
        factory.setDataSource(dataSource());
        factory.setJpaPropertyMap(jpaPropertyMap());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory( entityManagerFactory().getObject());
        return txManager;
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();

    }

    private Map<String,String> jpaPropertyMap() {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.dialect","org.hibernate.dialect.PostgreSQL82Dialect");
        map.put("hibernate.hbm2ddl.auto","none");
        map.put("hibernate.format_sql","true");

        return map;
    }
}
