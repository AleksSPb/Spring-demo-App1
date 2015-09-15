package com.springapp.mvc.controller;

import com.springapp.mvc.config.HtmlEscapeStringEditor;
import com.springapp.mvc.dao.People;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import com.springapp.mvc.dao.PeopleRepository;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;




@Controller
@RequestMapping({"/"})
public class HelloController {
    //NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
	PeopleRepository peopleRep;

    public HelloController() {
    }

  /*  @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }*/

    @RequestMapping(
            value = {"/check"},
            method = {RequestMethod.POST}
    )
    public String displayGetNameAge(@Valid People people, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "PeopleForm";
        } else {
            peopleRep.save(people);
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

        model.addAttribute("peoples", peopleRep.findAll());
        return "printDB";
    }
    
@InitBinder
public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(String.class, new HtmlEscapeStringEditor());
}
}