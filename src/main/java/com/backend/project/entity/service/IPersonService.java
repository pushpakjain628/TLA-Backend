package com.backend.project.entity.service;

import com.backend.project.entity.model.Person;

import java.util.List;

public interface IPersonService {

    public List<Person> getAllPersons();

    public Person savePerson(Person person);

    public Person getPersonById(Long id);

}
