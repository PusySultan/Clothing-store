package org.example.repository;

import org.example.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long>
{
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    Optional<Person> findByName(String name);

    // TODO any function
}
