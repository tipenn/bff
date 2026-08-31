package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory stand-in for a real database. Swap this for a Spring Data JPA
 * repository later — the service layer above it won't need to change.
 */
@Repository
public class PersonRepository {

    private final Map<Long, Person> people = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public PersonRepository() {
        save(new Person(null, "Juan", "Dela Cruz", 28));
        save(new Person(null, "Maria", "Santos", 34));
    }

    public List<Person> findAll() {
        return List.copyOf(people.values());
    }

    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(people.get(id));
    }

    public Person save(Person person) {
        if (person.getId() == null) {
            person.setId(idSequence.getAndIncrement());
        }
        people.put(person.getId(), person);
        return person;
    }

    public void deleteById(Long id) {
        people.remove(id);
    }
}
