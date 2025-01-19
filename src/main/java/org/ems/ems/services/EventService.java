package org.ems.ems.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ems.ems.models.Event;
import org.ems.ems.repository.EventRepository;

import java.util.List;

@ApplicationScoped
public class EventService {

    @Inject
    private EventRepository eventRepository;

    /**
     * Fetch all events.
     *
     * @return List of events.
     */
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    /**
     * Fetch an event by its ID.
     *
     * @param id The event ID.
     * @return Event if found, null otherwise.
     */
    public Event getEventById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid event ID");
        }
        return eventRepository.findById(id);
    }

    /**
     * Save or update an event.
     *
     * @param event The event to save.
     * @return true if the operation was successful.
     */
    public boolean saveEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        eventRepository.save(event);
        return true;
    }

    /**
     * Delete an event by its ID.
     *
     * @param id The event ID.
     * @return true if the event was successfully deleted.
     */
    public boolean deleteEvent(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid event ID");
        }
        Event event = eventRepository.findById(id);
        if (event == null) {
            return false; // Event not found
        }
        eventRepository.delete(id);
        return true;
    }

    public List<Event> searchEvents(String searchQuery) {
        return eventRepository.searchEvents(searchQuery);
    }
}
