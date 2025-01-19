package org.ems.ems.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ems.ems.models.User;
import org.ems.ems.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    /**
     * Fetch all users from the repository.
     *
     * @return List of users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Fetch a user by their ID.
     *
     * @param id The user ID.
     * @return User if found, null otherwise.
     */
    public User getUserById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        return userRepository.findById(id);
    }

    /**
     * Fetch a user by their Email.
     *
     * @param email The user Email.
     * @return User if found, null otherwise.
     */
    public User getUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Invalid user Email");
        }
        System.out.println("Fetching user with email: " + email);
        return userRepository.findByEmail(email);
    }

    /**
     * Save or update a user.
     *
     * @param user The user to save.
     * @return true if the operation was successful.
     */
    public boolean saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userRepository.save(user);
        return true;
    }

    /**
     * Delete a user by their ID.
     *
     * @param id The user ID.
     * @return true if the user was successfully deleted.
     */
    public boolean deleteUser(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        User user = userRepository.findById(id);
        if (user == null) {
            return false; // User not found
        }
        userRepository.delete(id);
        return true;
    }
}
