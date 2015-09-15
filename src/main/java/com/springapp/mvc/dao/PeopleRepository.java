
package com.springapp.mvc.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface PeopleRepository extends JpaRepository<People, Long> {

    List<People> findByName(String name);
    
}
