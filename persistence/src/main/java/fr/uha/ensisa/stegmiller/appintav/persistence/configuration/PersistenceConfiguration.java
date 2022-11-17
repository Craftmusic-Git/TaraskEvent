package fr.uha.ensisa.stegmiller.appintav.persistence.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = {"fr.uha.ensisa.stegmiller"}) // Prends en charge le contexte du module model
public class PersistenceConfiguration {

    @Bean(name="entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarask");
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

}
