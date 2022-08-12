package minibank.bbva.model.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "DepositoExtraccion")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DepositExtraction extends Movements {

	@NotNull(message = "{movimiento.cajaCajero}")
	@Column(updatable = false)
	private String cajaCajero;

	public String getCajaCajero() {
		return cajaCajero;
	}

	public void setCajaCajero(String cajaCajero) {
		this.cajaCajero = cajaCajero;
	}

}
