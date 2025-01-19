package org.ems.ems.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ems.ems.models.Event;
import org.ems.ems.models.Registration;
import org.ems.ems.models.User;
import org.ems.ems.repository.RegistrationRepository;

import java.util.List;

@ApplicationScoped
public class RegistrationService {

    @Inject
    private RegistrationRepository registrationRepository;

    /**
     * Fetch all registrations.
     *
     * @return List of registrations.
     */
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    /**
     * Fetch a registration by its ID.
     *
     * @param id The registration ID.
     * @return Registration if found, null otherwise.
     */
    public Registration getRegistrationById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid registration ID");
        }
        return registrationRepository.findById(id);
    }

    /**
     * Save or update a registration.
     *
     * @param registration The registration to save.
     * @return true if the operation was successful.
     */
    public boolean saveRegistration(Registration registration) {
        if (registration == null) {
            throw new IllegalArgumentException("Registration cannot be null");
        }
        registrationRepository.save(registration);
        return true;
    }

    /**
     * Delete a registration by its ID.
     *
     * @param id The registration ID.
     * @return true if the registration was successfully deleted.
     */
    public boolean deleteRegistration(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid registration ID");
        }
        Registration registration = registrationRepository.findById(id);
        if (registration == null) {
            return false; // Registration not found
        }
        registrationRepository.delete(id);
        return true;
    }

    public boolean isUserAlreadyRegistered(User user, Event event) {
        return getAllRegistrations().stream()
                .anyMatch(registration -> registration.getUser().equals(user) && registration.getEvent().equals(event));
    }

}
