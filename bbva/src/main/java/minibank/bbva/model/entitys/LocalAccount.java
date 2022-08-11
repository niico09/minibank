package minibank.bbva.model.entitys;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "CUENTAS_LOCALES")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LocalAccount extends Account {

	public void agregarMovimiento(Sells vta) {
		setSaldoActual(getSaldoActual() + (vta.getMonto() * vta.getCotizacion()));
		this.getMovimientos().add(vta);
		vta.setCuenta(this);
	}

	public void agregarMovimiento(Purchases cmp) {
		setSaldoActual(getSaldoActual() - (cmp.getMonto() * cmp.getCotizacion()));
		this.getMovimientos().add(cmp);
		cmp.setCuenta(this);
	}

}
