package minibank.bbva.model.entitys;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Address {

	@NotEmpty(message = "{direccion.calle}")
	private String calle;

	@NotEmpty(message = "{direccion.numero}")
	private String numero;
	private String departamento;
	private String piso;

	@NotEmpty(message = "{direccion.ciudad}")
	private String ciudad;

	@NotEmpty(message = "{direccion.codigoPostal}")
	private String codigoPostal;

	@NotEmpty(message = "{direccion.provincia}")
	private String provincia;

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

}
