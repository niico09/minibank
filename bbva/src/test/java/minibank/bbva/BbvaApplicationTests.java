package minibank.bbva;

import java.util.Date;

import javax.el.MethodNotFoundException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import minibank.bbva.model.entitys.Account;
import minibank.bbva.model.entitys.Movements;
import minibank.bbva.model.entitys.Person;
import minibank.bbva.model.entitys.enums.TypeMoney;
import minibank.bbva.model.service.AccountService;
import minibank.bbva.model.service.ChangeMoneyImpl;
import minibank.bbva.model.service.MediumTransfer;
import minibank.bbva.model.service.MovementService;
import minibank.bbva.model.service.PersonService;

@SpringBootTest
class BbvaApplicationTests {

	@Autowired
	PersonService personService;

	@Autowired
	AccountService accountService;

	@Autowired
	MediumTransfer mediumTransfer;

	@Autowired
	MovementService movementService;

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
	void verifyInit() {
		var person = personService.read("38742415").get();
		Assert.assertTrue(person != null);
		Assert.assertEquals("prueba2", person.getName());

		person = personService.read("38742415").get();
		Assert.assertTrue(person != null);
		Assert.assertEquals("prueba3", person.getName());
	}

	@Test
	void tasasCheck() {
		ChangeMoneyImpl change = new ChangeMoneyImpl();
		var result = change.cambiar(TypeMoney.EUR, TypeMoney.USA, 100.000);

		Assert.assertSame(1.05, result.getTasa());
		Assert.assertSame(1.05 * 100.000, result.getResultado());

		result = change.cambiar(TypeMoney.USA, TypeMoney.EUR, 100.000);
		Assert.assertSame(0.95, result.getTasa());
		Assert.assertSame(0.95 * 100.000, result.getResultado());

		result = change.cambiar(TypeMoney.USA, TypeMoney.USA, 100.000);
		Assert.assertSame(1, result.getTasa());
		Assert.assertSame(1 * 100.000, result.getResultado());
	}

	@Test
	void errorCreateAccount() {
		var account = new Account();
		account.setActualBalance(100.000);
		account.setAgreed("Si");
		account.setCreateDate(new Date());
		account.setDniOwner(327421234L);
		account.setEndDate(new Date());
		account.setInitalBalance(90.000);
		account.setNumber(01241010L);
		account.setPrimaryOwner(false);
		account.setTypeMoney(TypeMoney.EUR.toString());
		Assert.assertTrue(!accountService.save(account));
	}

	@Test
	void transferMoney() {
		var movements = new Movements();
		movements.setAmount(500);
		movements.setDayTransfer(new Date());
		movements.setDescription("prueba transferencia");
		// CUENTA EUR
		movements.setDestination("02165489");
		// CUENTA USA
		movements.setOrigin("02165421");

		Boolean checkResult = mediumTransfer.transfer(movements);
		Assert.assertTrue(checkResult);

		if (checkResult) {
			var listMovements = movementService.readSpecific(movements.getOrigin());

			Assert.assertTrue(!listMovements.isEmpty());

			listMovements.forEach(x -> {
				if (x.getOrigin().equals("02165421") && x.getDestination().equals("02165489")
						&& x.getDescription().equals("prueba transferencia")) {
					Assert.assertTrue(movements.getAmount() != x.getAmount());
				}
			});
		}

	}

	@Test
	void updatePersonErrorException() {
		var person = new Person();
		person.setCellphone("11234678");
		person.setDni(38742415L);
		person.setEmail("a@gmail.com");
		person.setLastName("a2");
		person.setName("prueba3");

		try {
			personService.update(person);
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "This method it's unsupportable in this moment.");
		}

	}

}
