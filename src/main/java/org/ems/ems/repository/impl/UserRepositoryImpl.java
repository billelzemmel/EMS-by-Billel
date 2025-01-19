package org.ems.ems.repository.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.ems.ems.models.User;
import org.ems.ems.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // No user found with the provided email
        }
    }


    @Override
    @Transactional
    public void save(User user) {
        if (user.getId() == null) {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } else {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
    }
}

