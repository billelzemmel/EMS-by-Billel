package org.ems.ems.repository;

import org.ems.ems.models.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(Long id);

    User findByEmail(String email);

    void save(User user);

    void delete(Long id);
}
