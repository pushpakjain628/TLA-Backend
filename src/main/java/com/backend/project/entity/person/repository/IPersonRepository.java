package com.backend.project.entity.person.repository;

import com.backend.project.entity.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<Person,Long> {
}
