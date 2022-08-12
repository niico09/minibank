package minibank.bbva.model.entitys;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "MOVIMIENTOS")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
		@NamedQuery(name = "MOVIMIENTOS.searchAccount", query = "select mov from MOVIMIENTOS mov where mov.cuenta.numero=:idCuenta"), })
@Data
@NoArgsConstructor
public abstract class Movements {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(updatable = false)
	@NotNull(message = "{movimiento.fechaHora}")
	@PastOrPresent(message = "{movimiento.fechaHora.pasado}")
	private LocalDateTime fechayHora;

	@Positive(message = "{movimiento.monto}")
	@Column(updatable = false)
	private Double monto;

	@NotEmpty(message = "{movimiento.descripcion}")
	@Column(updatable = false)
	private String descripcion;

	@ManyToOne
	@JsonIgnore
	Account cuenta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechayHora() {
		return fechayHora;
	}

	public void setFechayHora(LocalDateTime fechayHora) {
		this.fechayHora = fechayHora;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Account getCuenta() {
		return cuenta;
	}

	public void setCuenta(Account cuenta) {
		this.cuenta = cuenta;
	}

}
