package minibank.bbva.model.service;

import minibank.bbva.model.entitys.enums.TypeMoney;

public interface ServicioCambio {
	public ResultadoCambio cambiar(TypeMoney de, TypeMoney a, Double monto);
}
