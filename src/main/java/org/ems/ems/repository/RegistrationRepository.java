package org.ems.ems.repository;

import org.ems.ems.models.Registration;

import java.util.List;

public interface RegistrationRepository {

    List<Registration> findAll();

    Registration findById(Long id);

    void save(Registration registration);

    void delete(Long id);
}
