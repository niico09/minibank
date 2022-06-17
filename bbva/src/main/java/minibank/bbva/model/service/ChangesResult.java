package minibank.bbva.model.service;


public class ChangesResult implements ResultadoCambio {

	private double tasa;
	private double resultado;

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public void setResultado(double resultado) {
		this.resultado = resultado;
	}

	@Override
	public Double getTasa() {
		// TODO Auto-generated method stub
		return this.tasa;
	}

	@Override
	public Double getResultado() {
		// TODO Auto-generated method stub
		return this.resultado;
	}

}
