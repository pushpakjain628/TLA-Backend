package com.backend.project.entity.controller;

import com.backend.project.entity.model.Person;
import com.backend.project.entity.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@Validated
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping
    public List<Person> getListOfPersons(){
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long personId) {
        return personService.getPersonById(personId);
    }

    @PostMapping
    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }









}
