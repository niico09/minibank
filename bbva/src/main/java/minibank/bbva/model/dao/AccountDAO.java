package minibank.bbva.model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import minibank.bbva.model.entitys.Account;
import minibank.bbva.model.entitys.ForeignAccount;
import minibank.bbva.model.entitys.enums.TypeMoney;

@Repository("cuentaDAO")
public class AccountDAO {

	@PersistenceContext
	EntityManager entityManager;

	public AccountDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public AccountDAO() {
	}

	public void create(Account obj) {
		entityManager.persist(obj);
	}

	public Account read(Long id) {
		return entityManager.find(Account.class, id);
	}

	public Account update(Account t) {
		return (Account) entityManager.merge(t);

	}

	public void delete(Account t) {
		t = entityManager.merge(t);
		entityManager.remove(t);
	}

	public Collection<Account> readAll() {
		return (List<Account>) entityManager.createNamedQuery("CUENTAS.getAll").getResultList();
	}

	public List<ForeignAccount> getCuentaPorMoneda(TypeMoney moneda) {
		return (List<ForeignAccount>) entityManager.createNamedQuery("Cuenta.accountByMoney")
				.setParameter("moneda", moneda).getResultList();
	}

}
