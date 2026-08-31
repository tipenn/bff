package com.example.demo.controller;

import com.example.demo.dto.PersonRequest;
import com.example.demo.dto.PersonResponse;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Pure JSON API. A frontend (React, mobile app, etc.) calls this directly.
 * Notice: never touches Person directly in its signatures — only DTOs
 * go in and out.
 */
@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public List<PersonResponse> getAll() {
        return service.findAll().stream().map(PersonResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getOne(@PathVariable Long id) {
        return service.findById(id)
                .map(PersonResponse::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PersonResponse> create(@Valid @RequestBody PersonRequest request) {
        Person created = service.create(request.getFirstName(), request.getLastName(), request.getAge());
        return ResponseEntity.status(HttpStatus.CREATED).body(PersonResponse.from(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable Long id, @Valid @RequestBody PersonRequest request) {
        return service.update(id, request.getFirstName(), request.getLastName(), request.getAge())
                .map(PersonResponse::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
