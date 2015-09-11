package com.springapp.mvc.controller;

import com.springapp.mvc.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.springapp.mvc.dao.PeopleDAO;


@Controller
@RequestMapping({"/"})
public class HelloController {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
	PeopleDAO peopleDao;

    public HelloController() {
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }

    @RequestMapping(
            value = {"/check"},
            method = {RequestMethod.POST}
    )
    public String displayGetNameAge(@Valid People people, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "PeopleForm";
        } else {
            peopleDao.insert(people);
            redirectAttributes.addFlashAttribute("name", people.getName());
            redirectAttributes.addFlashAttribute("age", Integer.valueOf(people.getAge()));
            return "redirect:/printValue";
        }
    }

    @RequestMapping(
            value = {"/printValue"},
            method = {RequestMethod.GET}
    )
    public String displayResult(ModelMap model) {
        return "printValue";
    }

    @RequestMapping(
            method = {RequestMethod.GET}
    )
    public String displayPeopleForm(ModelMap model) {
        model.addAttribute("people", new People());
        return "PeopleForm";
    }

    @RequestMapping(
            value = {"/getDB"},
            method = {RequestMethod.GET}
    )
    public String displayPeoples(ModelMap model) {

        model.addAttribute("peoples", peopleDao.findAll());
        return "printDB";
    }
    
}