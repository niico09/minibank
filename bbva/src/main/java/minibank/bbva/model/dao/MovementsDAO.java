package minibank.bbva.model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import minibank.bbva.model.entitys.Movements;

@Repository("movimientoDAO")
public class MovementsDAO {

	@PersistenceContext
	EntityManager entityManager;

	public MovementsDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public MovementsDAO() {
	}

	public Movements find(Long id) {
		return entityManager.find(Movements.class, id);
	}

	public void create(Movements obj) {
		entityManager.persist(obj);
	}

	public Movements read(Long id) {
		return entityManager.find(Movements.class, id);
	}

	public Movements update(Movements t) {
		return (Movements) entityManager.merge(t);

	}

	public void delete(Movements t) {
		t = entityManager.merge(t);
		entityManager.remove(t);
	}

	public Collection<Movements> readAll() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Movements> getMovimientosPorCuenta(Long idCuenta) {
		return (List<Movements>) entityManager.createNamedQuery("MOVIMIENTOS.searchAccount")
				.setParameter("idCuenta", idCuenta).getResultList();
	}

}
