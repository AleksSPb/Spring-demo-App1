package com.springapp.mvc.controller;

import com.springapp.mvc.model.People;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping({"/"})
public class HelloController {
    public HelloController() {
    }

    @RequestMapping(
            value = {"/check"},
            method = {RequestMethod.POST}
    )
    public String displayGetNameAge(@Valid People people, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "PeopleForm";
        } else {
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
}