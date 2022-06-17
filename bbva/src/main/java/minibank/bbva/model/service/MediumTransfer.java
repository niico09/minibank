package minibank.bbva.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minibank.bbva.model.dao.DAO;
import minibank.bbva.model.entitys.Account;
import minibank.bbva.model.entitys.Movements;

@Service
public class MediumTransfer {

	@Autowired
	private DAO<Movements> movementsDAO;

	@Autowired
	private DAO<Account> accountDAO;

	public Boolean transfer(Movements movements) {

		if (verifiyAccounts(movements.getOrigin(), movements.getDestination())
				&& verifyMoney(movements.getOrigin(), movements.getAmount())) {

			movementsDAO.save(movements);
			updateAccount(movements.getOrigin(), 0, movements.getAmount());
			updateAccount(movements.getDestination(), 1, movements.getAmount());

		}
		return true;
	}

	// 0 = RESTA ; 1 = SUMA
	private void updateAccount(String accountTransfer, int i, double ammount) {

		Optional<Account> account = accountDAO.read(accountTransfer);

		if (!account.isEmpty()) {

			Account localAccount = account.get();

			if (i == 0) {
				var actualBalance = localAccount.getActualBalance() - ammount;
				localAccount.setActualBalance(actualBalance);
			} else {
				var actualBalance = localAccount.getActualBalance() + ammount;
				localAccount.setActualBalance(actualBalance);
			}
			accountDAO.update(localAccount);
		}

	}

	private boolean verifyMoney(String origin, double amount) {

		Optional<Movements> movement = movementsDAO.read(origin);

		if (!movement.isEmpty() && movement.get().getAmount() > amount) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	private boolean verifiyAccounts(String origin, String destination) {

		if (movementsDAO.read(origin).isEmpty())
			return Boolean.FALSE;

		if (movementsDAO.read(origin).isEmpty())
			return Boolean.FALSE;

		return Boolean.TRUE;
	}

}
