
package com.springapp.mvc.service;

import com.springapp.mvc.dao.People;
import com.springapp.mvc.dao.PeopleRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
public class PeopleService {
    
    @Autowired
    private PeopleRepository peopleRepository;
    
    
    @Transactional(readOnly=true)
    public List<People> getAll() {
        
        return peopleRepository.findAll();
        
    }
    
    @SuppressWarnings("AssignmentToMethodParameter")
    @Transactional
    public People saveAndFlush(People si) {
        
        if ( si != null ) {
            si = peopleRepository.saveAndFlush(si);
        }
        
        return si;
        
    }
    
    @Transactional    
    public void delete(long id) {
        
        peopleRepository.delete(id);
        
    }
    
}
