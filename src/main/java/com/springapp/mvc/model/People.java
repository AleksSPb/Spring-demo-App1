package com.springapp.mvc.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class People {

    private Integer id;

    @NotBlank
    @Size(min=2, max=50)
    private String name;

    @NotNull @Min(1) @Max(150)
    private Integer age;

    @Override
    public String toString() {
        return "People{id=" + id +", name='" + name + '\'' +", age=" + age +'}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
