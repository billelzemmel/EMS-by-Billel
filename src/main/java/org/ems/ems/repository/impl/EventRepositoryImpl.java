package org.ems.ems.repository.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.ems.ems.models.Event;
import org.ems.ems.repository.EventRepository;

import java.util.List;

@ApplicationScoped
public class EventRepositoryImpl implements EventRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Event> findAll() {
        return em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
    }

    @Override
    public Event findById(Long id) {
        return em.find(Event.class, id);
    }


    @Transactional
    @Override
    public void save(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        em.getTransaction().begin(); // Start a transaction
        if (event.getId() == null) {
            System.out.println("Saving event - Persist");
            em.persist(event);
        } else {
            System.out.println("Updating event - Merge");
            em.merge(event);
        }
        em.getTransaction().commit(); // Commit the transaction
    }


    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        Event event = findById(id);
        if (event != null) {
            em.getTransaction().begin(); // Start a transaction
            em.remove(event);
            em.getTransaction().commit(); // Commit the transaction
        }else{
            throw new IllegalArgumentException("Deleting event - Not Found");
        }
    }

    /**
     * @param searchQuery
     * @return
     */
    @Override
    public List<Event> searchEvents(String searchQuery) {
        // Query to search for events by title or description
        String jpql = "SELECT e FROM Event e WHERE LOWER(e.title) LIKE :search OR LOWER(e.description) LIKE :search";
        TypedQuery<Event> query = em.createQuery(jpql, Event.class);
        query.setParameter("search", "%" + searchQuery.toLowerCase() + "%");
        return query.getResultList();
    }
}
