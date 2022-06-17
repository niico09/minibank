package minibank.bbva.model.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minibank.bbva.model.entitys.Account;
import minibank.bbva.model.entitys.Movements;
import minibank.bbva.model.service.AccountService;
import minibank.bbva.model.service.MediumTransfer;
import minibank.bbva.model.service.MovementService;

@RestController
@RequestMapping("/bank/accounts")
public class AccountController {

	@Autowired
	AccountService accountService;

	@Autowired
	MediumTransfer mediumTransfer;

	@Autowired
	MovementService movementService;

	@GetMapping("/")
	List<Account> getAll() {
		return (List<Account>) accountService.readAll();
	}

	// TODO: Se agrega titular o cotitular
	@PostMapping("/")
	Boolean addAccount(@RequestBody Account account) {
		return accountService.save(account);
	}

	@PostMapping("/transfers")
	List<Movements> getMovements(@RequestBody Movements movements) {

		if (mediumTransfer.transfer(movements)) {
			return movementService.readSpecific(movements.getOrigin());
		}

		return null;
	}

}
