package com.journaldev.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.PersonService;

@Controller

//Reutilisé dans le context par la jsp
@RequestMapping("api")
public class SpringAjaxController {

	PersonService personService;
    
    @Autowired
    public SpringAjaxController(PersonService personService) {
        this.personService = personService;
    }
 
   
    @RequestMapping("person/random")
    @ResponseBody
    public Person randomPerson() {
        return personService.getRandom();
    }
 
    @RequestMapping("person/{id}")
    @ResponseBody
    public Person getById(@PathVariable int id) {
        return personService.getPersonById(id);
    }
     
    // same as above method, just showing different URL mapping
    @RequestMapping(value="person", params="id")
    @ResponseBody
    public Person getByIdFromParam(@RequestParam int id) {
        return personService.getPersonById(id);
    }
     
    // On recupere les données du formulaire serialisées et on ajoute la personne
    @RequestMapping(value="person", method=RequestMethod.POST)
    @ResponseBody
    public String savePerson(Person person) {
        personService.addPerson(person);
        //On retourne cette donnée à la JSP
        return "Personne ajoutée: " + person.toString();
    }
}
