package com.springapp.mvc.DAO;

import com.springapp.mvc.model.People;

import java.util.List;

public interface PeopleDAO {
    People findByName(String name);

    List<People> findAll();

}
