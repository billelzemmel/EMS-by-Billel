package org.ems.ems.utils;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventPU");

    @Produces
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    @PreDestroy
    public void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
