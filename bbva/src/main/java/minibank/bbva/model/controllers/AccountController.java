package minibank.bbva.model.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import minibank.bbva.model.dto.AccountDTO;
import minibank.bbva.model.dto.CotitularDTO;
import minibank.bbva.model.entitys.Account;
import minibank.bbva.model.entitys.Movements;
import minibank.bbva.service.AccountService;
import minibank.bbva.service.MovementService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@Autowired
	MovementService movementService;

	private EntityModel<Account> maping(Account account) {
		Link linkc = linkTo(methodOn(AccountController.class).getMovements(account.getNumero())).withRel("cuenta");
		return EntityModel.of(account, linkc);
	}

	@GetMapping("/movimientos/{idCuenta}")
	public List<Movements> getMovements(@PathVariable Long id) {
		return movementService.getMovementsFromAccountId(id);
	}

	@GetMapping(path = "/")
	public CollectionModel<EntityModel<Account>> listar() {
		List<EntityModel<Account>> cuentas = accountService.listAccount().stream().map(this::maping).toList();
		Link self = linkTo(methodOn(AccountController.class).listar()).withSelfRel();
		return CollectionModel.of(cuentas, self);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Account> alta(@RequestBody AccountDTO cuentaAlta) {
		try {
			accountService.altaCuenta(cuentaAlta.getSaldoInicial(), cuentaAlta.getDescubierto(),
					cuentaAlta.getIdCliente(), cuentaAlta.getMoneda());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return null;
	}

	@PostMapping("/cotitular")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Account> agregarCotitular(@RequestBody CotitularDTO cuentacotitular) {
		try {
			Account cta = accountService.addCotitular(cuentacotitular.getIdCliente(), cuentacotitular.getIdCuenta());
			return new ResponseEntity<>(cta, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/movements/account/{idCuenta}")
	public List<Movements> listarMovimientos(@PathVariable Long idCuenta) {
		return movementService.getMovementsFromAccountId(idCuenta);
	}

}
