package minibank.bbva.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minibank.bbva.model.dao.DAO;
import minibank.bbva.model.entitys.Account;
import minibank.bbva.model.entitys.Person;

@Service
public class Manager {

	@Autowired
	DAO<Person> personService;

	@Autowired
	DAO<Account> accountService;

	public Boolean createAccount(Account account) {

		if (verifyExitsPerson(account.getDniOwner()) && verifyPrimaryOwner(account.getNumber(), account.getPrimaryOwner())) {
			return accountService.save(account);
		}

		return Boolean.FALSE;
	}

	private boolean verifyPrimaryOwner(long number, Boolean primary) {

		var stringNumber = String.valueOf(number);

		if (accountService.read(stringNumber).isEmpty() && Boolean.TRUE.equals(primary)) {
			return Boolean.TRUE;
		} else if (verifyOnlyPrimary(number) && Boolean.FALSE.equals(primary)) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	private boolean verifyOnlyPrimary(long number) {

		var stringNumber = String.valueOf(number);
		var listAccount = accountService.readSpecific(stringNumber);

		for (Account ac : listAccount) {
			if (ac.getPrimaryOwner().equals(Boolean.TRUE))
				return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	private boolean verifyExitsPerson(Long dniOwner) {
		var stringDniOwner = String.valueOf(dniOwner);
		return !personService.read(stringDniOwner).isEmpty();
	}

}
