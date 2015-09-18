package com.springapp.mvc.service;

import com.springapp.mvc.model.People;

import java.util.List;

public interface PeopleService {

    People create(People people);

    List<People> getAll();
           
}
