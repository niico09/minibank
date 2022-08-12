package bbvaminibank.bbva.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.CALLS_REAL_METHODS;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;

import minibank.bbva.model.dao.AccountDAO;
import minibank.bbva.model.dao.ClientDAO;
import minibank.bbva.model.entitys.Account;
import minibank.bbva.model.entitys.Address;
import minibank.bbva.model.entitys.Client;
import minibank.bbva.model.entitys.ForeignAccount;

/**
 * TEST para DAO de Cuenta
 * 
 * @author Matias Castillo
 *
 */

class CuentaDAOTest {

	static EntityManagerFactory emf;
	static EntityManager em;
	static EntityTransaction tx;

	Address dir;
	Client cte;
	ClientDAO cteDao;
	Account cta;
	AccountDAO ctaDao;

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
		ctaDao = new AccountDAO(em);
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
	}

	@Test
	public void testCreateCuentaOk() {
		
		cte = createClient();

		cta = new ForeignAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);
		cteDao.create(cte);
		ctaDao.create(cta);
		em.flush();
		assertNotNull(cta.getNumero());
		em.clear();
		Account ctaguardada = em.find(Account.class, cta.getNumero());
		
		assertNotNull(ctaguardada);
		assertFalse(cta == ctaguardada);
	}

	@Test
	public void testReadCuentaOk() {

		cte = createClient();

		cta = new ForeignAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);
//		cta.setmoneda
		cteDao.create(cte);
		ctaDao.create(cta);
		em.flush();
		Account ctaguardada = ctaDao.read(cta.getNumero());
		assertTrue(ctaguardada.equals(cta));
	}

	@Test
	public void testUpdateCuentaOk() {

		cte = createClient();

		cta = new ForeignAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);
		cteDao.create(cte);
		ctaDao.create(cta);
		em.flush();
		assertNotNull(cta.getNumero());
		em.clear();
		cta.setFechaCierre(LocalDate.now());
		ctaDao.update(cta);
		em.flush();
		Account ctaeActualizada = ctaDao.read(cta.getNumero());
		assertTrue(!(ctaDao.read(ctaeActualizada.getNumero()) == null));
	}

	@Test
	public void testUpdateSaldoInicialOk() {

		cte = createClient();

		cta = new ForeignAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);
//		cta.setmoneda
		cteDao.create(cte);
		ctaDao.create(cta);
		em.flush();
		assertNotNull(cta.getNumero());
		em.clear();
		Account ctab = ctaDao.read(cta.getNumero());
		ctab.setSaldoInicial(12345D);
		ctaDao.update(ctab);
		em.flush();
		em.clear();
		Account ctaActualizada = ctaDao.read(ctab.getNumero());
		assertEquals(0, ctaActualizada.getSaldoInicial());
		assertEquals(12345D, ctab.getSaldoInicial());
	}

	private Client createClient() {

		cte = new Client();
		cte.setNombre("nombre");
		cte.setApellido("apellido");
		cte.setTelefono("telefono");
		cte.setEmail("email@email.com");

		dir = new Address();
		dir.setCalle("calle1");
		dir.setNumero("numero1");
		dir.setDepartamento("departamento1");
		dir.setPiso("piso1");
		dir.setCiudad("ciudad1");
		dir.setCodigoPostal("codigoPostal1");
		dir.setProvincia("provincia1");
		cte.setDireccion(dir);

		return cte;
	}

	@Test
	public void testDeleteCuentaOk() {
	
		cte = createClient();
		
		cta = new ForeignAccount();
		cta.setFechaCreacion(LocalDate.now());
		cta.setSaldoInicial(0D);
		cta.setSaldoActual(0D);
		cta.setDescubiertoAcordado(0D);
		cta.setFechaCierre(null);
		cta.setTitular(cte);
//		cta.setmoneda
		cteDao.create(cte);
		ctaDao.create(cta);
		em.flush();
		assertNotNull(cte.getId());
		em.clear();
		Account ctaeActualizada = ctaDao.read(cta.getNumero());
		assertTrue(em.contains(ctaeActualizada));
		ctaDao.delete(ctaeActualizada);
		assertTrue(!em.contains(ctaeActualizada));
		assertTrue(!em.contains(cta));
	}

	@AfterEach
	public void finalCadaTest() {
		tx.rollback();
	}

}
