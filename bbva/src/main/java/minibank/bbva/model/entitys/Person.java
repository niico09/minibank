package minibank.bbva.model.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Person {

	@Id
	private Long id;

	@NotNull(message = "dni it's required")
	private Long dni;
	@NotNull(message = "name it's required")
	private String name;
	@NotNull(message = "lastname it's required")
	private String lastName;
	@Column
	private String cellphone;
	@Column
	private String email;

	public Person() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Person(Long id, @NotNull Long dni, @NotNull String name, @NotNull String lastName, String cellphone,
			String email) {
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
		this.cellphone = cellphone;
		this.email = email;
	}

}
