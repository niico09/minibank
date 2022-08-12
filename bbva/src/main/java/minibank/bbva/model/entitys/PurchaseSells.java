package minibank.bbva.model.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Positive;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "CompraVenta")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PurchaseSells extends Movements {

	@Positive(message = "{movimiento.cotizacion}")
	@Column(updatable = false)
	private Double cotizacion;
	@Column(updatable = false)
	private Double comision;

	public Double getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Double cotizacion) {
		this.cotizacion = cotizacion;
	}

	public Double getComision() {
		return comision;
	}

	public void setComision(Double comision) {
		this.comision = comision;
	}

}
