package org.example.repositoryTest;

import org.example.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepositoryTest extends CrudRepository<Person, Integer>
{
    // TODO any function
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    Optional<Person> findByName(String name);
    Optional<Person> findByEmail(String email);
}
