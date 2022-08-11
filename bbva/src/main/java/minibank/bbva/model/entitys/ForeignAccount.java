package minibank.bbva.model.entitys;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import minibank.bbva.model.entitys.enums.TypeMoney;

@Entity(name = "CUENTAS_EXTRANJERAS")
@NamedQueries({
		@NamedQuery(name = "Cuenta.accountByMoney", query = "select cta from CUENTAS_EXTRANJERAS cta where cta.moneda=:moneda") })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ForeignAccount extends Account {

	@Enumerated(EnumType.STRING)
	private TypeMoney moneda;

	public void agregarMovimiento(Sells vta) {
		setSaldoActual(getSaldoActual() - vta.getMonto() - vta.getComision());
		this.getMovimientos().add(vta);
		vta.setCuenta(this);
	}

	public void agregarMovimiento(Purchases cmp) {
		setSaldoActual(getSaldoActual() + cmp.getMonto());
		this.getMovimientos().add(cmp);
		cmp.setCuenta(this);
	}

	public TypeMoney getMoneda() {
		return moneda;
	}

	public void setMoneda(TypeMoney moneda) {
		this.moneda = moneda;
	}

}
