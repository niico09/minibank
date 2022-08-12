package bbvaminibank.bbva.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import minibank.bbva.model.dao.AccountDAO;
import minibank.bbva.service.MovementService;

/**
 * TEST para las operaciones de Transferir y Vender
 * 
 * @author Matias castillo
 *
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:/spring/contexto-jpa-test.xml")
@Transactional
class MovimientoServicioTest {
	@Autowired
	private MovementService servicioMovimiento;

	@Autowired
	private AccountDAO ctaDao;

	@BeforeEach
	public void inicioCadaTest() {

	}

	@Test
	public void testTransferirMontoCero() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.transferir(1L, 1L, 0.0);
		});
		assertEquals("Monto de transferencia debe ser mayor a CERO", excep.getMessage());
	}

	@Test
	public void testTransferirCuentaOrigenInexistente() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.transferir(100L, 1L, 123.0);
		});
		assertEquals("Cuenta Origen inexistente", excep.getMessage());
	}

	@Test
	public void testTransferirCuentaDestinoInexistente() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.transferir(1L, 100L, 123.0);
		});
		assertEquals("Cuenta Destino inexistente", excep.getMessage());
	}

	@Test
	public void testTransferirCuentaOrigenCerrada() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.transferir(2L, 1L, 123.0);
		});
		assertEquals("Cuenta origen cerrada", excep.getMessage());
	}

	@Test
	public void testTransferirCuentaDestinoCerrada() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.transferir(1L, 2L, 99.0);
		});
		assertEquals("Cuenta destino cerrada", excep.getMessage());
	}

	@Test
	public void testTransferirSaldoInsuficiente() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.transferir(1L, 2L, 9999999.0);
		});
		assertEquals("Saldo insuficiente", excep.getMessage());
	}

	@Test
	public void transferirOK() {
		Double monto = 150.0;

		Double saldoOrigenAntes;
		Double saldoOrigenDespues;
		Double saldoDestinoAntes;
		Double saldoDestinoDespues;

		saldoOrigenAntes = ctaDao.read(1L).getSaldoActual();
		saldoDestinoAntes = ctaDao.read(3L).getSaldoActual();
		assertEquals(0, ctaDao.read(1L).getMovimientos().size());
		assertEquals(0, ctaDao.read(3L).getMovimientos().size());

		servicioMovimiento.transferir(1L, 3L, monto);

		assertEquals(1, ctaDao.read(1L).getMovimientos().size());
		assertEquals(1, ctaDao.read(3L).getMovimientos().size());

		saldoOrigenDespues = ctaDao.read(1L).getSaldoActual();
		saldoDestinoDespues = ctaDao.read(3L).getSaldoActual();

		assertTrue(saldoOrigenAntes == (saldoOrigenDespues + monto));
		assertTrue(saldoDestinoAntes == (saldoDestinoDespues - monto));

	}

	@Test
	public void testVentaMontoCero() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.vender(1L, 2L, 3L, 0.0);
		});
		assertEquals("Monto de venta debe ser mayor a CERO", excep.getMessage());
	}

	@Test
	public void testClienteInexistente() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.vender(10L, 2L, 3L, 100.0);
		});
		assertEquals("Cliente inexistente", excep.getMessage());
	}

	@Test
	public void testVentaCuentaOrigenInexistente() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.vender(1L, 10L, 3L, 100.0);
		});
		assertEquals("Cuenta Origen inexistente", excep.getMessage());
	}

	@Test
	public void testVentaCuentaDestinoInexistente() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.vender(1L, 2L, 30L, 100.0);
		});
		assertEquals("Cuenta Destino inexistente", excep.getMessage());
	}

	@Test
	public void testVentaSaldoInsuficiente() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.vender(1L, 5L, 1L, 99999.0);
		});
		assertEquals("Saldo insuficiente", excep.getMessage());
	}

	@Test
	public void testVentaCuentaOrigenCerrada() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.vender(2L, 6L, 3L, 100.0);
		});
		assertEquals("Cuenta origen cerrada", excep.getMessage());
	}

	@Test
	public void testVentaCuentaDestinoCerrada() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioMovimiento.vender(2L, 5L, 2L, 100.0);
		});
		assertEquals("Cuenta destino cerrada", excep.getMessage());
	}

}
