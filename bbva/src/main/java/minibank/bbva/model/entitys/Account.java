package minibank.bbva.model.entitys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "CUENTAS")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ @NamedQuery(name = "CUENTAS.getAll", query = "select ctas from CUENTAS ctas"), })
@Data
@NoArgsConstructor
public abstract class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;

	@Column(updatable = false)
	private LocalDate fechaCreacion;

	@Column(updatable = false)
	private Double saldoInicial;

	private Double saldoActual;

	private Double descubiertoAcordado;
	private LocalDate fechaCierre;

	@ManyToOne
	@JoinColumn(name = "titular_Id", updatable = false)
	@JsonIgnore
	private Client titular;

	@ManyToMany
	@JsonIgnore
	private Set<Client> cotitulares = new HashSet<Client>();
	@OneToMany(mappedBy = "cuenta")
	private List<Movements> movimientos = new ArrayList<Movements>();

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(Double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public Double getDescubiertoAcordado() {
		return descubiertoAcordado;
	}

	public void setDescubiertoAcordado(Double descubiertoAcordado) {
		this.descubiertoAcordado = descubiertoAcordado;
	}

	public LocalDate getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(LocalDate fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Client getTitular() {
		return titular;
	}

	public void setTitular(Client titular) {
		this.titular = titular;
	}

	public Set<Client> getCotitulares() {
		return cotitulares;
	}

	public void setCotitulares(Set<Client> cotitulares) {
		this.cotitulares = cotitulares;
	}

	public List<Movements> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movements> movimientos) {
		this.movimientos = movimientos;
	}

}
