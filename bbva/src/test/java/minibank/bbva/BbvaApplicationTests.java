package minibank.bbva;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import minibank.bbva.model.dao.DAO;
import minibank.bbva.model.entitys.Account;
import minibank.bbva.model.entitys.Person;
import minibank.bbva.model.entitys.enums.TypeMoney;

@SpringBootTest
class BbvaApplicationTests {

	@Autowired
	DAO<Person> personService;

	@Autowired
	DAO<Account> accountService;

	@BeforeAll
	void init() {

		var person = new Person();
		person.setCellphone("11234678");
		person.setDni(38742415L);
		person.setEmail("prueba@gmail.com");
		person.setLastName("apellido");
		person.setName("prueba2");
		personService.save(person);

		person = new Person();
		person.setCellphone("11234678");
		person.setDni(38742410L);
		person.setEmail("prueba@gmail.com");
		person.setLastName("apellido");
		person.setName("prueba3");

		var account = new Account();
		account.setActualBalance(100.000);
		account.setAgreed("Si");
		account.setCreateDate(new Date());
		account.setDniOwner(38742415L);
		account.setEndDate(new Date());
		account.setInitalBalance(90.000);
		account.setNumber(3164789132L);
		account.setPrimaryOwner(true);
		account.setTypeMoney(TypeMoney.EUR.toString());
		accountService.save(account);

		account.setDniOwner(38742410L);
		account.setPrimaryOwner(false);
		accountService.save(account);

	}

	@Test
	void contextLoads() {

	}

	@Test
	void verifyInit() {
		var person = personService.read("38742415").get();
		Assert.assertTrue(person != null);
		Assert.assertEquals("prueba2", person.getName());

		person = personService.read("38742415").get();
		Assert.assertTrue(person != null);
		Assert.assertEquals("prueba3", person.getName());
	}

}
