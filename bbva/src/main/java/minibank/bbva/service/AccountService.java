package minibank.bbva.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import minibank.bbva.model.dao.AccountDAO;
import minibank.bbva.model.dao.ClientDAO;
import minibank.bbva.model.entitys.Account;
import minibank.bbva.model.entitys.Client;
import minibank.bbva.model.entitys.ForeignAccount;
import minibank.bbva.model.entitys.LocalAccount;
import minibank.bbva.model.entitys.enums.TypeMoney;

@Component
public class AccountService {

	@Resource(name = "clienteDAO")
	private ClientDAO clienteDao;

	@Resource(name = "cuentaDAO")
	private AccountDAO cuentaDao;

	@Transactional
	public Collection<Account> listAccount() {
		return (List<Account>) cuentaDao.readAll();
	}

	@Transactional
	public Account accountById(Long id) {
		Account account = cuentaDao.read(id);
		return account != null ? account : null;
	}

	@Transactional
	public void altaCuenta(Double saldoInicial, Double descubiertoAcordado, Long id, TypeMoney moneda) {
		Client titular = clienteDao.read(id);

		if (titular.getId() == null) {
			throw new IllegalArgumentException("El cliente titular no existe.");
		}

		Account cuenta;
		if (moneda == null) {
			cuenta = new LocalAccount();
			cuenta.setFechaCreacion(LocalDate.now());
			cuenta.setSaldoInicial(saldoInicial);
			cuenta.setSaldoActual(saldoInicial);
			cuenta.setDescubiertoAcordado(0.0D);
			cuenta.setTitular(titular);
		} else {
			cuenta = new ForeignAccount();
			cuenta.setFechaCreacion(LocalDate.now());
			cuenta.setSaldoInicial(saldoInicial);
			cuenta.setSaldoActual(saldoInicial);
			cuenta.setDescubiertoAcordado(0.0D);
			cuenta.setTitular(titular);
			((ForeignAccount) cuenta).setMoneda(moneda);
			;
		}
		cuentaDao.create(cuenta);
	}

	@Transactional
	public Account addCotitular(Long idCliente, Long idCuenta) {

		Client cte = clienteDao.read(idCliente);
		Account cta = cuentaDao.read(idCuenta);

		Set<Client> cotitulares;

		if (cte == null) {
			throw new IllegalArgumentException("Cliente Inexistente");
		}

		if (cta == null) {
			throw new IllegalArgumentException("Cuenta Inexistente");
		}

		if (!cuentaAbierta(cta)) {
			throw new IllegalArgumentException("Cuenta cerrada");
		}
		if (cta.getTitular().getId() == cte.getId()) {
			throw new IllegalArgumentException("El cliente ya es titular de la cuenta");
		}
		cotitulares = cta.getCotitulares();
		for (Client c : cotitulares) {
			if (c.getId() == cte.getId())
				throw new IllegalArgumentException("El cliente ya es cotitular de la cuenta");
		}

		internalAddCotitular(cte, cta);
		cuentaDao.update(cta);

		return cta;
	}

	private void internalAddCotitular(Client cte, Account cta) {
		if (cta.getCotitulares().contains(cte)) {
			throw new IllegalArgumentException("Cliente ya es cotitular de la cuenta");
		} else {
			var listCotitulares = cta.getCotitulares();
			listCotitulares.add(cte);
		}

	}

	private boolean cuentaAbierta(Account cta) {
		return cta.getFechaCierre() == null;
	}

}
