package com.example.demo.dto;

import com.example.demo.model.Person;

/**
 * The exact JSON shape sent BACK to the client. This is the one place
 * you control what the outside world is allowed to see — if Person ever
 * grows an internal-only field, it simply never gets added here.
 */
public class PersonResponse {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final int age;

    public PersonResponse(Long id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public static PersonResponse from(Person person) {
        return new PersonResponse(person.getId(), person.getFirstName(), person.getLastName(), person.getAge());
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
