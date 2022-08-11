package minibank.bbva.model.entitys;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "TransferenciaCredito")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TransferCredit extends Transfer {

	@ManyToOne
	@NotNull(message = "{movimiento.cuentaOrigen}")
	@JsonIgnore
	private Account cuentaOrigen;

	public Account getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Account cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

}
