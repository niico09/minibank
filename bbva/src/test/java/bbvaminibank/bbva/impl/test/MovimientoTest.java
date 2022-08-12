package bbvaminibank.bbva.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import minibank.bbva.model.entitys.Account;
import minibank.bbva.model.entitys.Address;
import minibank.bbva.model.entitys.Client;
import minibank.bbva.model.entitys.Deposit;
import minibank.bbva.model.entitys.Extraction;
import minibank.bbva.model.entitys.LocalAccount;
import minibank.bbva.model.entitys.Movements;
import minibank.bbva.model.entitys.Purchases;
import minibank.bbva.model.entitys.Sells;
import minibank.bbva.model.entitys.TransferCredit;
import minibank.bbva.model.entitys.TransferDebit;

/**
 * TEST para COnstructores y Validaciones de atributos de Movimientos
 * 
 * @author Matias Castillo
 */

public class MovimientoTest {

	Address dir;
	Client cte;
	Account cta;
	Movements mov;
	Deposit dep;
	Extraction ext;
	Purchases cmp;
	Sells vnt;
	TransferCredit cred;
	TransferDebit debi;

	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	@BeforeEach
	public void crear() {
		var dir = new Address();
		dir.setNumero("numero1");
		dir.setPiso("piso1");
		dir.setCiudad("ciudad1");
		dir.setCodigoPostal("codigoPostal1");
		dir.setProvincia("provincia1");
		dir.setCalle("calle1");
		dir.setDepartamento("departamento1");
		var cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);
		cta = new LocalAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);
	}

	@Test
	public void testContructorDepositoOk() {
		dep = new Deposit();
		dep.setFechayHora(LocalDateTime.now());
		dep.setMonto(2000D);
		dep.setDescripcion("deposito 1");
		dep.setCajaCajero("cajaCajero 1");
		Double monto = 2000D;
		String descripcion = "deposito 1";
		String cajero = "cajaCajero 1";
		assertEquals(monto, dep.getMonto(), 0);
		assertEquals(descripcion, dep.getDescripcion());
		assertEquals(cajero, dep.getCajaCajero());
	}

	@Test
	public void testContructorExtraccionOk() {
		ext = new Extraction();
		ext.setFechayHora(LocalDateTime.now());
		ext.setMonto(500D);
		ext.setDescripcion("extraccion 1");
		ext.setCajaCajero("cajaCajero 1");
		Double monto = 500D;
		String descripcion = "extraccion 1";
		String cajero = "cajaCajero 1";
		assertEquals(monto, ext.getMonto(), 500D);
		assertEquals(descripcion, ext.getDescripcion());
		assertEquals(cajero, ext.getCajaCajero());
	}

	@Test
	public void testContructorCompraOk() {
		cmp = new Purchases();
		cmp.setFechayHora(LocalDateTime.now());
		cmp.setMonto(500D);
		cmp.setDescripcion("compra-dolares");
		cmp.setCotizacion(205D);
		cmp.setComision(5D);
		Double monto = 500D;
		String descripcion = "compra-dolares";
		Double cotizacion = 205D;
		Double comision = 5D;
		assertEquals(monto, cmp.getMonto(), 0);
		assertEquals(descripcion, cmp.getDescripcion());
		assertEquals(cotizacion, cmp.getCotizacion(), 0);
		assertEquals(comision, cmp.getComision(), 0);
	}

	@Test
	public void testContructorVentaOk() {
		vnt = new Sells();
		vnt.setFechayHora(LocalDateTime.now());
		vnt.setMonto(100D);
		vnt.setDescripcion("venta-dolares");
		vnt.setCotizacion(195D);
		vnt.setComision(5D);

		Double monto = 100D;
		String descripcion = "venta-dolares";
		Double cotizacion = 195D;
		Double comision = 5D;
		assertEquals(monto, vnt.getMonto(), 0);
		assertEquals(descripcion, vnt.getDescripcion());
		assertEquals(cotizacion, vnt.getCotizacion(), 0);
		assertEquals(comision, vnt.getComision(), 0);
	}

	@Test
	public void testContructorCreditoOk() {
		cred = new TransferCredit();
		cred.setFechayHora(LocalDateTime.now());
		cred.setMonto(35000D);
		cred.setDescripcion("credito-tran");
		cred.setCuentaOrigen(cta);

		Double monto = 35000D;
		String descripcion = "credito-tran";
		assertEquals(monto, cred.getMonto(), 0);
		assertEquals(descripcion, cred.getDescripcion());
		assertTrue(cred.getCuentaOrigen().equals(cta));
	}

	@Test
	public void testContructorDebitoOk() {
		debi = new TransferDebit();
		debi.setFechayHora(LocalDateTime.now());
		debi.setMonto(5000D);
		debi.setDescripcion("debito-tran");
		debi.setCuentaDestino(cta);

		Double monto = 5000D;
		String descripcion = "debito-tran";
		assertEquals(monto, debi.getMonto(), 0);
		assertEquals(descripcion, debi.getDescripcion());
		assertTrue(debi.getCuentaDestino().equals(cta));
	}

	@Test
	public void testValidacionCamposObligatoriosTransDebi() {
		LocalDateTime fechaHora = null;
		Double monto = 0D;
		String descripcion = "";
		Account cta = null;

		debi = new TransferDebit();
		debi.setFechayHora(fechaHora);
		debi.setMonto(monto);
		debi.setDescripcion(descripcion);
		debi.setCuentaDestino(cta);

		Set<ConstraintViolation<TransferDebit>> violations = validator.validate(debi);
		assertTrue(!violations.isEmpty());
		assertEquals(4, violations.size());

	}

	@Test
	public void testValidacionCamposObligatoriosTransCred() {
		LocalDateTime fechaHora = null;
		Double monto = 0D;
		String descripcion = "";
		Account cta = null;

		cred = new TransferCredit();
		cred.setFechayHora(fechaHora);
		cred.setMonto(monto);
		cred.setDescripcion(descripcion);
		cred.setCuentaOrigen(cta);

		Set<ConstraintViolation<TransferCredit>> violations = validator.validate(cred);
		assertTrue(!violations.isEmpty());
		assertEquals(4, violations.size());

	}

}
