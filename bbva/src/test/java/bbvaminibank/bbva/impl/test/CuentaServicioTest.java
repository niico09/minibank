package bbvaminibank.bbva.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import minibank.bbva.model.entitys.Account;
import minibank.bbva.service.AccountService;

/**
 * TEST de Agregar Cotitular en una Cuenta
 * 
 * @author Matias Castillo
 *
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:/spring/contexto-jpa-test.xml")
@Transactional
class CuentaServicioTest {
	@Autowired
	private AccountService servicioCuenta;

	@BeforeEach
	public void inicioCadaTest() {

	}

	@Test
	public void testAgregarCotitularClienteInexistente() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioCuenta.addCotitular(50L, 1L);
		});
		assertEquals("Cliente Inexistente", excep.getMessage());
	}

	@Test
	public void testAgregarCotitularCuentaInexistente() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioCuenta.addCotitular(1L, 50L);
		});
		assertEquals("Cuenta Inexistente", excep.getMessage());
	}

	@Test
	public void testAgregarCotitularCuentaCerrada() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioCuenta.addCotitular(1L, 2L);
		});
		assertEquals("Cuenta cerrada", excep.getMessage());
	}

	@Test
	public void testAgregarCotitularClienteEsTitular() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioCuenta.addCotitular(1L, 1L);
		});
		assertEquals("El cliente ya es titular de la cuenta", excep.getMessage());
	}

	@Test
	public void testAgregarCotitularClienteEsCoTitular() {
		IllegalArgumentException excep = assertThrows(IllegalArgumentException.class, () -> {
			servicioCuenta.addCotitular(1L, 4L);
		});
		assertEquals("El cliente ya es cotitular de la cuenta", excep.getMessage());
	}

	@Test
	public void testAagregarCotitulaOK() {
		Account cta = servicioCuenta.addCotitular(4L, 1L);
		assertEquals(1, cta.getCotitulares().size(), 0);

	}

}
