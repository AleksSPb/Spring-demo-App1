package com.springapp.mvc.repository;

import com.springapp.mvc.model.People;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Integer> {


}
