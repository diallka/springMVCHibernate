package com.journaldev.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.PersonService;

//On definit que la classe est un controlleur
@Controller
public class PersonController {

	private PersonService personService;

	// Injection du service
	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
	
	//Test map
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String mapPage() {
		return "map";
	}
	
	//Test map Ajax
		@RequestMapping(value = "/mapAjax", method = RequestMethod.GET)
		public String mapAjaxPage(Model m, ModelMap mp) {
			Person p = new Person();
			p.setName("Abdoul");
			p.setCountry("France");
			p.setLatitude(50.640266);
			p.setLongitude(3.074592);
			//On met l'objet créé dans notre ModeMap
			mp.put("abdoul", p);
			m.addAttribute("personnes", personService.listPersons());
			return "map_ajax";
		}
	//Test interaction Spring Mvc / Ajax
	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	public String homePage(Model m) {
		m.addAttribute("personnes", personService.listPersons());
		return "spring_ajax";
	}

	@RequestMapping(value = { "/", "/persons" }, method = RequestMethod.GET)
	public String listPersons(Model model) {
		Person personne = new Person();
		personne.setAge(18);
		model.addAttribute("person", personne);
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}

	// Ajouter et modifier une personne
	@RequestMapping(value = "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p) {

		if (p.getId() == 0) {
			// Si inexistant on ajoute
			this.personService.addPerson(p);
		} else {
			// sinon on met à jour
			this.personService.updatePerson(p);
		}

		return "redirect:/persons";

	}

	// On supprime une entrée
	@RequestMapping("/remove/{id}")
	public String removePerson(@PathVariable("id") int id) {

		this.personService.removePerson(id);
		return "redirect:/persons";
	}

	@RequestMapping("/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", this.personService.getPersonById(id));
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}

}
