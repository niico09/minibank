package minibank.bbva.model.service;

public interface ResultadoCambio {
	/**
	 *  @return Tasa aplicada al cambio
	 */
	public Double getTasa();
	/**
	 * @return El resultado de la conversion
	 */
	public Double getResultado();
}
