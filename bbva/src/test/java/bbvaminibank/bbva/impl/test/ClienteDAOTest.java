package bbvaminibank.bbva.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import minibank.bbva.model.dao.ClientDAO;
import minibank.bbva.model.entitys.Address;
import minibank.bbva.model.entitys.Client;

/**
 * TEST para DAO de Cliente
 * 
 * @author Matias Castillo
 *
 */

class ClienteDAOTest {

	static EntityManagerFactory emf;
	static EntityManager em;
	static EntityTransaction tx;

	Address dir;
	Client cte;
	ClientDAO cteDao;

	@BeforeAll
	public static void InicioTest() {
		emf = Persistence.createEntityManagerFactory("minibancoPU");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	@BeforeEach
	public void inicioCadaTest() {
		tx.begin();
		cteDao = new ClientDAO(em);
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


	@AfterEach
	public void finalCadaTest() {
		tx.rollback();
	}

}
