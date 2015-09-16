package com.springapp.mvc.service;

import com.springapp.mvc.model.People;
import java.util.List;

public interface PeopleService {

    public People create(People people);
    public List<People> getAll();
           
}
