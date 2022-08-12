package bbvaminibank.bbva.impl.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import minibank.bbva.model.dao.MovementsDAO;
import minibank.bbva.model.entitys.Extraction;

/**
 * TEST para DAO de Movimiento
 * 
 * @author Matias Castillo
 *
 */

class MovimientoDAOTest {

	static EntityManagerFactory emf;
	static EntityManager em;
	static EntityTransaction tx;

	Extraction ext;
	MovementsDAO movDao;

	@BeforeAll
	public static void InicioTest() {
		emf = Persistence.createEntityManagerFactory("minibancoPU");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	@BeforeEach
	public void inicioCadaTest() {
		tx.begin();
		movDao = new MovementsDAO(em);
	}

	@Test
	public void testCreateExtraccionOk() {
		ext = new Extraction();
		ext.setFechayHora(LocalDateTime.now());
		ext.setMonto(2000.0);
		ext.setDescripcion("extraccion 1");
		ext.setCajaCajero("cajaCajero 1");
		movDao.create(ext);
		em.flush();
		Extraction movguardado = em.find(Extraction.class, ext.getId());
		assertTrue(movguardado.equals(ext));
	}

	@AfterEach
	public void finalCadaTest() {
		tx.rollback();
	}

}
