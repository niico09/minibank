package minibank.bbva.model.service;

import org.springframework.stereotype.Service;

import minibank.bbva.model.entitys.enums.TypeMoney;

@Service
public class ChangeMoneyImpl implements ServicioCambio {

	// 1 USA = 1,05 EUR

	@Override
	public ResultadoCambio cambiar(TypeMoney de, TypeMoney a, Double monto) {

		var changes = new ChangesResult();

		if (de.equals(TypeMoney.EUR) && a.equals(TypeMoney.USA)) {
			changes.setTasa(1.05);
			changes.setResultado(monto * 1.05);
		} else if (de.equals(TypeMoney.USA) && a.equals(TypeMoney.EUR)) {
			changes.setTasa(0.95);
			changes.setResultado(monto * 0.95);
		} else {
			changes.setTasa(1);
			changes.setResultado(monto * 1);
		}

		return changes;
	}

}
