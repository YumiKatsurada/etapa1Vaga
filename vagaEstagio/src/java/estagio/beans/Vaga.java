/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.beans;

import java.util.List;

/**
 *
 * @author yumi
 */
//Classe referente a vaga de estagio
public class Vaga {
    private 
        String title, city, state, zipcode, hirer, description;
        float salary;
        Categoria category;
        List<Requisito> requirements;
        int id;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getHirer() {
        return hirer;
    }

    public void setHirer(String hirer) {
        this.hirer = hirer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

    public List<Requisito> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requisito> requirements) {
        this.requirements = requirements;
    }
        
        
    
}
