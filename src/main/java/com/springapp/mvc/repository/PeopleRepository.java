package com.springapp.mvc.repository;

import com.springapp.mvc.model.People;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PeopleRepository extends JpaRepository<People, Integer> {


}
