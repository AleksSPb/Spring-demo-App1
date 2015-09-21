package com.springapp.mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "peoples")
public class People implements java.io.Serializable{

    @Id
    @GeneratedValue
     private long id;

    @Size(min=2, max=50)
    private String name;

    @NotNull @Min(1) @Max(150)
    private Integer age;

    public People() {}
    
    public People(String name, Integer age)   {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "People{id=" + id +", name='" + name + '\'' +", age=" + age +'}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
