package com.journaldev.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.PersonService;

@Controller
//Reutilis� dans le context par la jsp
@RequestMapping( "map" )
public class MapController {

    PersonService personService;

    @Autowired
    public MapController( final PersonService personService ) {
        this.personService = personService;
    }

    @RequestMapping( "person/random" )
    @ResponseBody
    public Person list() {
        return this.personService.getRandom();

    }

    @RequestMapping( "person/{id}" )
    @ResponseBody
    public Person getById( @PathVariable final int id ) {
        return this.personService.getPersonById( id );
    }

    // same as above method, just showing different URL mapping
    //    @RequestMapping( value = "person" , params = "id" )
    //    @ResponseBody
    //    public Person getByIdFromParam( @RequestParam final int id ) {
    //        return this.personService.getPersonById( id );
    //    }

    // On recupere les donn�es du formulaire serialis�es et on ajoute la personne
    @RequestMapping( value = "person" , method = RequestMethod.POST )
    @ResponseBody
    public String savePerson( final Person person ) {
        this.personService.addPerson( person );
        //On retourne cette donn�e � la JSP
        return "Personne ajout�e: " + person.toString();
    }
}
