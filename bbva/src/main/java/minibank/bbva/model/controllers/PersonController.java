package minibank.bbva.model.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minibank.bbva.model.entitys.Person;
import minibank.bbva.model.service.PersonService;

@RestController
@RequestMapping("/bank/persons")
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping("/")
	List<Person> getAll() {
		return (List<Person>) personService.readAll();
	}

	@GetMapping("/name/{name}")
	List<Person> getPersonByName(@PathVariable String name) {
		return personService.getForName(name);
	}

}
