package minibank.bbva.model.entitys;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "TransferenciaDebito")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TransferDebit extends Transfer {

	@ManyToOne
	@NotNull(message = "{movimiento.cuentaDestino}")
	@JsonIgnore
	private Account cuentaDestino;

	public Account getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Account cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

}
