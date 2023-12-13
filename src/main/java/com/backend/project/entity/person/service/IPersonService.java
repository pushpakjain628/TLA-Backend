package com.backend.project.entity.person.service;

import com.backend.project.entity.person.model.Person;

import java.util.List;

public interface IPersonService {

    public List<Person> getAllPersons();

    public Person savePerson(Person person);

    public Person getPersonById(Long id);

}
