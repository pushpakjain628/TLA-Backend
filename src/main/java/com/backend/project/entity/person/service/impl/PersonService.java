package com.backend.project.entity.person.service.impl;

import com.backend.project.entity.person.exception.ResourceNotFoundException;
import com.backend.project.entity.person.model.Person;
import com.backend.project.entity.person.repository.IPersonRepository;
import com.backend.project.entity.person.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person not found id: "+personId));
    }
}
