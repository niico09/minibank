package bbvaminibank.bbva.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

/**
 * TEST de Constructores, Metodos, Validaciones de atributos de clientes
 * 
 * @author Matias Castillo
 *
 */
public class ClienteTest {

	Address dir = new Address();
	Client cte;
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	@BeforeEach
	public void crearDireccion() {

	}

	@Test
	public void testContructorClienteOk() {

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

		String nombre = "nombre";
		String apellido = "apellido";
		String telefono = "telefono";
		String email = "email@email.com";
		assertEquals(nombre, cte.getNombre());
		assertEquals(apellido, cte.getApellido());
		assertEquals(telefono, cte.getTelefono());
		assertEquals(email, cte.getEmail());
		assertTrue(cte.getDireccion().equals(dir));
	}

	@Test
	public void testValidacionCamposObligatorios() {
		var cte = new Client();
		cte.setNombre("");
		cte.setApellido("");
		cte.setTelefono("");
		cte.setEmail("");
		cte.setDireccion(null);

		Set<ConstraintViolation<Client>> violations = validator.validate(cte);
		assertTrue(!violations.isEmpty());
		assertEquals(3, violations.size());

	}

	@Test
	public void testClienteCambioDatosOk() {
		var cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);
		String nombre = "nombre";
		String apellido = "apellido";
		String telefono = "telefono";
		String email = "email@email.com";
		assertEquals(nombre, cte.getNombre());
		assertEquals(apellido, cte.getApellido());
		assertEquals(telefono, cte.getTelefono());
		assertEquals(email, cte.getEmail());
		assertTrue(cte.getDireccion().equals(dir));
		cte.setNombre("nuevoNombre");
		cte.setApellido("nuevoApellido");
		cte.setTelefono("nuevoTelefono");
		cte.setEmail("nuevoEmail@email.com");
		assertEquals("nuevoNombre", cte.getNombre());
		assertEquals("nuevoApellido", cte.getApellido());
		assertEquals("nuevoTelefono", cte.getTelefono());
		assertEquals("nuevoEmail@email.com", cte.getEmail());
	}

	@Test
	public void testClienteCambioDireccionOk() {
		var cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");

		var dir = new Address();
		dir.setNumero("numero1");
		dir.setPiso("piso1");
		dir.setCiudad("ciudad1");
		dir.setCodigoPostal("codigoPostal1");
		dir.setProvincia("provincia1");
		dir.setCalle("calle1");
		dir.setDepartamento("departamento1");

		cte.setDireccion(dir);
		assertEquals("calle1", cte.getDireccion().getCalle());
		assertEquals("numero1", cte.getDireccion().getNumero());

		dir = new Address();
		dir.setNumero("nuevoNumero");
		dir.setPiso("");
		dir.setCiudad("ciudad1");
		dir.setCodigoPostal("codigoPostal1");
		dir.setProvincia("provincia1");
		dir.setCalle("otraCalle");
		dir.setDepartamento("");

		cte.setDireccion(dir);

		assertEquals("otraCalle", cte.getDireccion().getCalle());
		assertEquals("nuevoNumero", cte.getDireccion().getNumero());

	}

	@Test
	public void testEsTitular() {
		Account cta1 = mock(Account.class);
		var cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);
		cte.getCuentasTitular().add(cta1);
		assertTrue(cte.getCuentasTitular().contains(cta1));
	}

	@Test
	public void testEsCoTitular() {
		Account cta1 = mock(Account.class);
		var cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);
		cte.getCuentasCoTitular().add(cta1);
		cte.getCuentasCoTitular().contains(cta1);
		assertTrue(cte.getCuentasCoTitular().contains(cta1));
	}

	@Test
	public void testAgregarCuentaCotitular() {

		Account cta1 = mock(Account.class);
		Account cta2 = mock(Account.class);
		Account cta3 = mock(Account.class);
		Account cta4 = mock(Account.class);
		Account cta5 = mock(Account.class);

		var cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);
		cte.agregarCuentaCoTitular(cta1);
		cte.agregarCuentaCoTitular(cta2);
		cte.agregarCuentaCoTitular(cta3);
		cte.agregarCuentaCoTitular(cta4);
		cte.agregarCuentaCoTitular(cta5);

		assertEquals(5, cte.getCuentasCoTitular().size());
	}

	@Test
	public void testAgregarCuentaCotitularYaEsCotitular() {
		Account cta1 = mock(Account.class);
		Account cta2 = mock(Account.class);
		Account cta3 = mock(Account.class);
		Account cta4 = mock(Account.class);
		Account cta5 = mock(Account.class);

		var cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);

		try {
			cta1.getCotitulares().add(cte);
			cta2.getCotitulares().add(cte);
			cta3.getCotitulares().add(cte);
			cta4.getCotitulares().add(cte);
			cta5.getCotitulares().add(cte);
		} catch (IllegalArgumentException e) {
			assertEquals("Cliente ya es cotitular de la cuenta", e.getMessage());
		}
	}

	@Test
	public void testQuitarCuentaCotitular() {

		Account cta1 = mock(Account.class);
		Account cta2 = mock(Account.class);
		Account cta3 = mock(Account.class);
		Account cta4 = mock(Account.class);
		Account cta5 = mock(Account.class);

		var cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);

		cte.agregarCuentaCoTitular(cta1);
		cte.agregarCuentaCoTitular(cta2);
		cte.agregarCuentaCoTitular(cta3);
		cte.agregarCuentaCoTitular(cta4);
		cte.agregarCuentaCoTitular(cta5);

		assertEquals(5, cte.getCuentasCoTitular().size());

		cte.quitarCuentaCoTitular(cta4);
		cte.quitarCuentaCoTitular(cta5);
		assertEquals(3, cte.getCuentasCoTitular().size());

	}

	@Test
	public void testQuitarCuentaCotitularInexistente() {
		Account cta1 = mock(Account.class);
		Account cta2 = mock(Account.class);
		Account cta3 = mock(Account.class);
		Account cta4 = mock(Account.class);
		Account cta5 = mock(Account.class);
		cta1.getCotitulares().add(cte);
		cta2.getCotitulares().add(cte);
		cta3.getCotitulares().add(cte);
		cta4.getCotitulares().add(cte);
		cta5.getCotitulares().add(cte);

		var cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);
		cte.agregarCuentaCoTitular(cta1);
		cte.agregarCuentaCoTitular(cta2);
		cte.agregarCuentaCoTitular(cta3);
		cte.agregarCuentaCoTitular(cta4);

		assertEquals(4, cte.getCuentasCoTitular().size());

		try {
			cta5.getCotitulares().remove(cte);

		} catch (IllegalArgumentException e) {
			assertEquals("Cliente no es cotitular de la cuenta", e.getMessage());
		}

	}

	@Test
	public void testCuentaDelCliente() {
		Account cta1 = mock(Account.class);
		Account cta2 = mock(Account.class);

		when(cta1.getNumero()).thenReturn(1L);
		when(cta2.getNumero()).thenReturn(2L);

		var cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);

		cta1.getCotitulares().add(cte);

		assertTrue(!cte.cuentaDelCliente(cta1));
		assertTrue(!cte.cuentaDelCliente(cta2));

	}

}
