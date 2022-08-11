package minibank.bbva.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO {

	private Long idCtaOrigen;
	private Long idCtaDestino;
	private Double monto;

	public Long getIdCtaOrigen() {
		return idCtaOrigen;
	}

	public void setIdCtaOrigen(Long idCtaOrigen) {
		this.idCtaOrigen = idCtaOrigen;
	}

	public Long getIdCtaDestino() {
		return idCtaDestino;
	}

	public void setIdCtaDestino(Long idCtaDestino) {
		this.idCtaDestino = idCtaDestino;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

}
