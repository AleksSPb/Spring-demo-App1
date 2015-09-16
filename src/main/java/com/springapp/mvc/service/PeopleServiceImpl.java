package com.springapp.mvc.service;

import com.springapp.mvc.model.People;
import com.springapp.mvc.repository.PeopleRepository;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeopleServiceImpl implements PeopleService{
    
    @Resource
    private PeopleRepository peopleRepository;
    
    @Override
    @Transactional
    public People create(People people) {
        People createdPeople=people;
        return peopleRepository.save(createdPeople);
    }
    
    @Override
    @Transactional
    public List<People> getAll() {
        return peopleRepository.findAll();
    }
}
