package minibank.bbva.impl;

import minibank.bbva.impl.inter.ResultChange;
import minibank.bbva.model.entitys.enums.TypeMoney;

public class ResultadoImpl implements ResultChange {

	TypeMoney de;
	TypeMoney a;
	Double monto;

	public ResultadoImpl() {
	}

	public ResultadoImpl(TypeMoney de, TypeMoney a, Double monto) {
		super();
		this.de = de;
		this.a = a;
		this.monto = monto;
	}

	@Override
	public Double getTasa() {
		// TODO Auto-generated method stub

		switch (de) {
		case USD:
			return 200.0;
		case EUR:
			return 300.0;
		default:
			break;
		}
		return null;
	}

	@Override
	public Double getResultado() {
		return monto * getTasa();
	}

}
