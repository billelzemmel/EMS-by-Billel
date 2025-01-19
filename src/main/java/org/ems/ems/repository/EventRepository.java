package org.ems.ems.repository;

import org.ems.ems.models.Event;

import java.util.List;

public interface EventRepository {

    List<Event> findAll();

    Event findById(Long id);

    void save(Event event);

    void delete(Long id);

    List<Event> searchEvents(String searchQuery);
}

