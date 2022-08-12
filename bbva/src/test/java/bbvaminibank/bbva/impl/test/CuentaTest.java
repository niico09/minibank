package bbvaminibank.bbva.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
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
import minibank.bbva.model.entitys.ForeignAccount;
import minibank.bbva.model.entitys.LocalAccount;
import minibank.bbva.model.entitys.Purchases;
import minibank.bbva.model.entitys.Sells;
import minibank.bbva.model.entitys.TransferCredit;
import minibank.bbva.model.entitys.TransferDebit;

/**
 * TEST de Constructor, Metodos y Validacion de atributos
 * 
 * @author Matias Castillo
 */
public class CuentaTest {

	Address dir;
	Client cte;
	Account cta;
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	public Client crearDireccion() {
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

		return cte;
	}

	@Test
	public void testContructorCuentaLocalOk() {

		cte = crearDireccion();

		cta = new LocalAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);

		LocalDate fechacreacion = LocalDate.now();
		Double saldoInicial = 0.0;
		Double saldoActual = 0.0;
		Double descubierto = 0.0;
		LocalDate fechaCierre = null;

		assertEquals(fechacreacion, cta.getFechaCreacion());
		assertEquals(saldoInicial, cta.getSaldoInicial(), 0);
		assertEquals(saldoActual, cta.getSaldoActual(), 0);
		assertEquals(descubierto, cta.getDescubiertoAcordado(), 0);
		assertEquals(fechaCierre, cta.getFechaCierre());
		assertTrue(cta.getTitular().equals(cte));
	}

	@Test
	public void testContructorCuentaExtranjeraOk() {

		cte = crearDireccion();

		cta = new ForeignAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);
		LocalDate fechacreacion = LocalDate.now();
		Double saldoInicial = 0.0;
		Double saldoActual = 0.0;
		Double descubierto = 0.0;

		assertEquals(fechacreacion, cta.getFechaCreacion());
		assertEquals(saldoInicial, cta.getSaldoInicial(), 0);
		assertEquals(saldoActual, cta.getSaldoActual(), 0);
		assertEquals(descubierto, cta.getDescubiertoAcordado(), 0);
		assertTrue(cta.getTitular().equals(cte));
	}

	@Test
	public void testCuentaLocalCambioSaldoOk() {

		cte = crearDireccion();

		cta = new LocalAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);

		LocalDate fechacreacion = LocalDate.now();
		Double saldoInicial = 0.0;
		Double saldoActual = 0.0;
		Double descubierto = 0.0;
		LocalDate fechaCierre = null;

		assertEquals(fechacreacion, cta.getFechaCreacion());
		assertEquals(saldoInicial, cta.getSaldoInicial(), 0);
		assertEquals(saldoActual, cta.getSaldoActual(), 0);
		assertEquals(descubierto, cta.getDescubiertoAcordado(), 0);
		assertEquals(fechaCierre, cta.getFechaCierre());
		assertTrue(cta.getTitular().equals(cte));

		cta.setSaldoActual(500.0);
		assertEquals(500.0, cta.getSaldoActual(), 0);

	}

	@Test
	public void testCuentaExtranjeraCambioSaldoOk() {

		cte = crearDireccion();

		cta = new ForeignAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);
		LocalDate fechacreacion = LocalDate.now();
		Double saldoInicial = 0.0;
		Double saldoActual = 0.0;
		Double descubierto = 0.0;
		LocalDate fechaCierre = null;

		assertEquals(fechacreacion, cta.getFechaCreacion());
		assertEquals(saldoInicial, cta.getSaldoInicial(), 0);
		assertEquals(saldoActual, cta.getSaldoActual(), 0);
		assertEquals(descubierto, cta.getDescubiertoAcordado(), 0);
		assertEquals(fechaCierre, cta.getFechaCierre());
		assertTrue(cta.getTitular().equals(cte));

		cta.setSaldoActual(500.0);
		assertEquals(500.0, cta.getSaldoActual(), 0);
	}

	@Test
	public void testCuentaLocalCambioTitularOk() {

		cte = crearDireccion();

		cta = new LocalAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);

		LocalDate fechacreacion = LocalDate.now();
		Double saldoInicial = 0.0;
		Double saldoActual = 0.0;
		Double descubierto = 0.0;
		LocalDate fechaCierre = null;

		assertEquals(fechacreacion, cta.getFechaCreacion());
		assertEquals(saldoInicial, cta.getSaldoInicial(), 0);
		assertEquals(saldoActual, cta.getSaldoActual(), 0);
		assertEquals(descubierto, cta.getDescubiertoAcordado(), 0);
		assertEquals(fechaCierre, cta.getFechaCierre());
		assertTrue(cta.getTitular().equals(cte));

		Client nuevotitular = mock(Client.class);

		cta.setTitular(nuevotitular);
		assertEquals(nuevotitular, cta.getTitular());

	}

	@Test
	public void testCuentaExtranjeraCambioTitularOk() {

		cte = crearDireccion();

		cta = new ForeignAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);
		LocalDate fechacreacion = LocalDate.now();
		Double saldoInicial = 0.0;
		Double saldoActual = 0.0;
		Double descubierto = 0.0;
		LocalDate fechaCierre = null;

		assertEquals(fechacreacion, cta.getFechaCreacion());
		assertEquals(saldoInicial, cta.getSaldoInicial(), 0);
		assertEquals(saldoActual, cta.getSaldoActual(), 0);
		assertEquals(descubierto, cta.getDescubiertoAcordado(), 0);
		assertEquals(fechaCierre, cta.getFechaCierre());
		assertTrue(cta.getTitular().equals(cte));

		Client nuevotitular = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);

		cta.setTitular(nuevotitular);
		assertEquals(nuevotitular, cta.getTitular());
	}

	@Test
	public void testValidacionCamposObligatorios() {

		cta = new LocalAccount();
		cta.setFechaCreacion(null);
		cta.setSaldoInicial(null);
		cta.setSaldoActual(null);
		cta.setDescubiertoAcordado(null);
		cta.setFechaCierre(null);
		cta.setTitular(null);

		Set<ConstraintViolation<Account>> violations = validator.validate(cta);
		assertTrue(!violations.isEmpty());
		assertEquals(3, violations.size());

	}

	@Test
	public void testAgregarMovimientoCuenta() {

		TransferCredit mov1 = mock(TransferCredit.class);
		TransferDebit mov2 = mock(TransferDebit.class);
		Deposit mov3 = mock(Deposit.class);
		Extraction mov4 = mock(Extraction.class);
		Purchases mov5 = mock(Purchases.class);
		Sells mov6 = mock(Sells.class);

		cta = new LocalAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);

		cta.agregarMovimiento(mov1);
		cta.agregarMovimiento(mov2);
		cta.agregarMovimiento(mov3);
		cta.agregarMovimiento(mov4);
		cta.agregarMovimiento(mov5);
		cta.agregarMovimiento(mov6);

		assertEquals(6, cta.getMovimientos().size());
	}

	@Test
	public void testAgregarCotitular() {

		Client cte1 = mock(Client.class);
		Client cte2 = mock(Client.class);
		Client cte3 = mock(Client.class);
		Client cte4 = mock(Client.class);
		Client cte5 = mock(Client.class);

		cta = new LocalAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);

		cta.getCotitulares().add(cte1);
		cta.getCotitulares().add(cte2);
		cta.getCotitulares().add(cte3);
		cta.getCotitulares().add(cte4);
		cta.getCotitulares().add(cte5);

		assertEquals(5, cta.getCotitulares().size());
	}

	@Test
	public void testAgregarCotitularDuplicado() {
		Client cte1 = mock(Client.class);
		Client cte2 = mock(Client.class);
		Client cte3 = mock(Client.class);

		cta = new LocalAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);

		try {
			cta.getCotitulares().add(cte1);
			cta.getCotitulares().add(cte2);
			cta.getCotitulares().add(cte3);
		} catch (IllegalArgumentException e) {
			assertEquals("Cliente ya es cotitular de la cuenta", e.getMessage());
		}

	}

	@Test
	public void testQuitarCotitular() {

		Client cte1 = mock(Client.class);
		Client cte2 = mock(Client.class);
		Client cte3 = mock(Client.class);
		Client cte4 = mock(Client.class);
		Client cte5 = mock(Client.class);

		cta = new LocalAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);

		cta.getCotitulares().add(cte1);
		cta.getCotitulares().add(cte2);
		cta.getCotitulares().add(cte3);
		cta.getCotitulares().add(cte4);
		cta.getCotitulares().add(cte5);

		assertEquals(5, cta.getCotitulares().size());

		cta.getCotitulares().remove(cte1);

		assertEquals(4, cta.getCotitulares().size());
	}

	@Test
	public void testCuentaDelClienteOK() {
		cta = new LocalAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);

//		assertTrue(cte.cuentaDelCliente(cta));

	}

	@Test
	public void testCuentaDelClienteMal() {
		Client cte1 = mock(Client.class);

		cta = new LocalAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);

//		assertTrue(!cte1.cuentaDelCliente(cta));

	}

}
