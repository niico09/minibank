package minibank.bbva.model.entitys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "CLIENTES")
@NamedQueries({
		@NamedQuery(name = "CLIENTES.searchName", query = "select cte from CLIENTES cte where cte.nombre=:nombre"),
		@NamedQuery(name = "CLIENTES.getAll", query = "select cte from CLIENTES cte") })
@Data
@NoArgsConstructor
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido;

	private String telefono;
	private String email;

	@Embedded
	private Address direccion;

	@OneToMany(mappedBy = "titular")
	private List<Account> cuentasTitular = new ArrayList<Account>();
	@ManyToMany(mappedBy = "cotitulares")
	private List<Account> cuentasCoTitular = new ArrayList<Account>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getDireccion() {
		return direccion;
	}

	public void setDireccion(Address direccion) {
		this.direccion = direccion;
	}

	public List<Account> getCuentasTitular() {
		return cuentasTitular;
	}

	public void setCuentasTitular(List<Account> cuentasTitular) {
		this.cuentasTitular = cuentasTitular;
	}

	public List<Account> getCuentasCoTitular() {
		return cuentasCoTitular;
	}

	public void setCuentasCoTitular(List<Account> cuentasCoTitular) {
		this.cuentasCoTitular = cuentasCoTitular;
	}

}
