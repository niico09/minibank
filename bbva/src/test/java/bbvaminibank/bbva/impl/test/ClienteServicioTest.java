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

import minibank.bbva.model.dao.ClientDAO;
import minibank.bbva.model.entitys.Address;
import minibank.bbva.model.entitys.Client;
import minibank.bbva.service.ServiceClient;

/**
 * TEST para Alta de Cliente y Cambio de Direccion
 * 
 * @author Matias Castillo
 *
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:/spring/contexto-jpa-test.xml")
@Transactional
class ClienteServicioTest {
	@Autowired
	private ServiceClient servicioCliente;
	@Autowired
	private ClientDAO cteDao;

	@BeforeEach
	public void inicioCadaTest() {

	}

	@Test
	public void testAltaCliente() {
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
		cteDao.create(cte);
		assertEquals("nombre", cte.getNombre());
		assertEquals("apellido", cte.getApellido());
	}

	@Test
	public void testClienteById() {
		Client cte = servicioCliente.readById(1L);
		assertEquals("nombre1", cte.getNombre());
	}

	@Test
	public void testClienteCambioDireccion() {
		var dir = new Address();
		dir.setNumero("nro");
		dir.setPiso("");
		dir.setCiudad("ciudad1");
		dir.setCodigoPostal("cp");
		dir.setProvincia("provincia");
		dir.setCalle("nueva calle");
		dir.setDepartamento("dtp");

		Client cte = servicioCliente.readById(1L);
		cte.setDireccion(dir);
		Client cte1 = servicioCliente.readById(1L);

		assertEquals("nueva calle", cte1.getDireccion().getCalle());
		assertEquals("nro", cte1.getDireccion().getNumero());
		assertEquals("dtp", cte1.getDireccion().getDepartamento());
		assertEquals("", cte1.getDireccion().getPiso());
		assertEquals("ciudad1", cte1.getDireccion().getCiudad());
		assertEquals("cp", cte1.getDireccion().getCodigoPostal());
		assertEquals("provincia", cte1.getDireccion().getProvincia());
	}
}
