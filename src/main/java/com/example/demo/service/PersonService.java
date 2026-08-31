package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * All the actual business rules live here — the controller should stay
 * thin and just delegate to this class.
 */
@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return repository.findById(id);
    }

    public Person create(String firstName, String lastName, int age) {
        Person person = new Person(null, firstName, lastName, age);
        return repository.save(person);
    }

    public Optional<Person> update(Long id, String firstName, String lastName, int age) {
        return repository.findById(id).map(existing -> {
            existing.setFirstName(firstName);
            existing.setLastName(lastName);
            existing.setAge(age);
            return repository.save(existing);
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
