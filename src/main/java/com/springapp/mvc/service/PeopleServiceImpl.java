package com.springapp.mvc.service;

import com.springapp.mvc.model.People;
import com.springapp.mvc.repository.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService{
    
    @Resource
    private PeopleRepository peopleRepository;
    
    @Override
    @Transactional
    public People create(People people) {

        return peopleRepository.save(people);
    }
    
    @Override
    @Transactional
    public List<People> getAll() {
        return peopleRepository.findAll();
    }
}
