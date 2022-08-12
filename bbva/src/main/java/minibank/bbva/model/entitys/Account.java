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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

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

	@NotNull(message = "{cuenta.fechaCreacion}")
	@PastOrPresent(message = "{cuenta.fechaCreacion.pasado}")
	@Column(updatable = false)
	private LocalDate fechaCreacion;

	@PositiveOrZero(message = "{cuenta.saldoInicial}")
	@Column(updatable = false)
	private Double saldoInicial;

	@NotNull(message = "{cuenta.saldoActual}")
	private Double saldoActual;

	@PositiveOrZero(message = "{cuenta.descubiertoAcordado}")
	private Double descubiertoAcordado;
	private LocalDate fechaCierre;

	@ManyToOne
	@JoinColumn(name = "titular_Id", updatable = false)
	@NotNull(message = "{cuenta.titular}")
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

	public void agregarMovimiento(TransferDebit transferDebit) {
		saldoActual = saldoActual - transferDebit.getMonto();
		movimientos.add(transferDebit);
		transferDebit.setCuenta(this);
	}

	public void agregarMovimiento(TransferCredit transferCredit) {
		saldoActual = saldoActual + transferCredit.getMonto();
		movimientos.add(transferCredit);
		transferCredit.setCuenta(this);

	}

	public void agregarMovimiento(Deposit deposito) {
		saldoActual = saldoActual + deposito.getMonto();
		movimientos.add(deposito);
		deposito.setCuenta(this);
	}

	public void agregarMovimiento(Extraction extraccion) {
		saldoActual = saldoActual - extraccion.getMonto();
		movimientos.add(extraccion);
		extraccion.setCuenta(this);
	}

	public abstract void agregarMovimiento(Sells vta);

	public abstract void agregarMovimiento(Purchases cmp);

}
