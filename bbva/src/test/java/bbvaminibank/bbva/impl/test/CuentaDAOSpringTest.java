package bbvaminibank.bbva.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import minibank.bbva.model.dao.AccountDAO;
import minibank.bbva.model.dao.ClientDAO;
import minibank.bbva.model.entitys.Account;
import minibank.bbva.model.entitys.Address;
import minibank.bbva.model.entitys.Client;
import minibank.bbva.model.entitys.ForeignAccount;

/**
 * 
 * @author Matias Castillo
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:/spring/contexto-jpa.xml")
@Transactional
class CuentaDAOSpringTest {

	@Autowired
	private ClientDAO cteDao;
	@Autowired
	private AccountDAO ctaDao;
	@PersistenceContext
	private EntityManager em;

	Address dir;
	Client cte;
	Account cta;

	public Client createClient() {
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
	public void testCreateCuentaOk() {
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
		Account ctaguardada = em.find(Account.class, cta.getNumero());
		assertTrue(!ctaguardada.equals(cta));
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

		assertTrue(!(ctaDao.read(ctaeActualizada.getNumero()).getFechaCierre() == null));
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
		cteDao.create(cte);
		ctaDao.create(cta);
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
		cteDao.create(cte);
		ctaDao.create(cta);
		cteDao.create(cte);
		ctaDao.create(cta);
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

}
