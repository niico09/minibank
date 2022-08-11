package minibank.bbva.inter;

import minibank.bbva.model.entitys.enums.TypeMoney;

public interface ServiceChange {
	public ResultChange change(TypeMoney de, TypeMoney a, Double monto);

}
