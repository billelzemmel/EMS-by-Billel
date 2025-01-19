package org.ems.ems.repository.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ems.ems.models.Registration;
import org.ems.ems.repository.RegistrationRepository;

import java.util.List;

@ApplicationScoped
public class RegistrationRepositoryImpl implements RegistrationRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Registration> findAll() {
        return em.createQuery("SELECT r FROM Registration r", Registration.class).getResultList();
    }

    @Override
    public Registration findById(Long id) {
        return em.find(Registration.class, id);
    }

    @Override
    @Transactional
    public void save(Registration registration) {
        if (registration.getId() == null) {
            em.persist(registration);
        } else {
            em.merge(registration);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Registration registration = findById(id);
        if (registration != null) {
            em.remove(registration);
        }
    }
}
