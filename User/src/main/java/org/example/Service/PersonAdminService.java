package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.model.BodyRequest;
import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PersonAdminService
{
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /// Получить всех пользователей
    public ResponseEntity<?> getPersons(BodyRequest bodyRequest)
    {
        if(checkAdmin(bodyRequest))
        {
            return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("У вас нет прав на получение результата этого запроса");
    }

    ///  Удалить данные всех пользователей
    @Transactional
    public void deleteAllPersons(BodyRequest bodyRequest)
    {
        if(checkAdmin(bodyRequest))
        {
            personRepository.deleteAll();
        }
    }

    private boolean checkAdmin(BodyRequest bodyRequest)
    {
        if(personRepository.existsByEmail(bodyRequest.getEmail()))
        {
            Person person = personRepository.findByEmail(bodyRequest.getEmail()).get();
            String role = person.getRole();
            String password = person.getPassword();

            if (Objects.equals(role, "Admin") &&
                    passwordEncoder.matches(bodyRequest.getPassword(), password))
            {
                return true;
            }
        }

        return false;
    }
}
