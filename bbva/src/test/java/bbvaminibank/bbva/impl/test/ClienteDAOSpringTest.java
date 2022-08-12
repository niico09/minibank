package bbvaminibank.bbva.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

/**
 * 
 * @author Matias Castillo
 *
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:/spring/contexto-jpa-test.xml")
@Transactional
class ClienteDAOSpringTest {

	@Autowired
	private ClientDAO cteDao;
	@PersistenceContext
	private EntityManager em;

	Address dir;
	Client cte;

	@BeforeEach
	public void inicioCadaTest() {
		dir = new Address();
		dir.setNumero("numero1");
		dir.setPiso("piso1");
		dir.setCiudad("ciudad1");
		dir.setCodigoPostal("codigoPostal1");
		dir.setProvincia("provincia1");
		dir.setCalle("calle1");
		dir.setDepartamento("departamento1");
	}

	@Test
	public void testCreateClienteOk() {
		cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);

		cteDao.create(cte);
		em.flush();
		assertNotNull(cte.getId());
		em.clear();
		Client cteguardado = em.find(Client.class, cte.getId());
		assertNotNull(cteguardado);
		assertFalse(cte == cteguardado);
		assertEquals(cte.getNombre(), cteguardado.getNombre());
	}

	@Test
	public void testReadClienteOk() {
		cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);
		cteDao.create(cte);
		em.flush();
		assertNotNull(cte.getId());
		em.clear();
		Client cteguardado = cteDao.read(cte.getId());
		assertNotNull(cteguardado);
		assertFalse(cte == cteguardado);
		assertEquals(cte.getNombre(), cteguardado.getNombre());
	}

	@Test
	public void testUpdateClienteOk() {
		cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);
		cteDao.create(cte);
		em.flush();
		assertNotNull(cte.getId());
		em.clear();
		cte.setNombre("nuevo nombre");
		cteDao.update(cte);
		em.flush();
		Client cteActualizado = cteDao.read(cte.getId());
		assertEquals("nuevo nombre", cteActualizado.getNombre());
	}

	@Test
	public void testDeleteClienteOk() {
		cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");
		cte.setDireccion(dir);
		cteDao.create(cte);
		em.flush();
		assertNotNull(cte.getId());
		em.clear();
		Client cteActualizado = cteDao.read(cte.getId());
		assertTrue(em.contains(cteActualizado));
		cteDao.delete(cteActualizado);
		assertTrue(!em.contains(cteActualizado));
	}

}
