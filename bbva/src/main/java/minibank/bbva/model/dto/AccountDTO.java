package minibank.bbva.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import minibank.bbva.model.entitys.enums.TypeMoney;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {

	private Double saldoInicial;
	private Double descubierto;
	private Long idCliente;
	private TypeMoney moneda;

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Double getDescubierto() {
		return descubierto;
	}

	public void setDescubierto(Double descubierto) {
		this.descubierto = descubierto;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public TypeMoney getMoneda() {
		return moneda;
	}

	public void setMoneda(TypeMoney moneda) {
		this.moneda = moneda;
	}

}
