package minibank.bbva.model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import minibank.bbva.model.entitys.Client;

@Repository("clienteDAO")
public class ClientDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public ClientDAO() {

	}

	public ClientDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public void create(Client client) {
		entityManager.persist(client);
	}

	public Client read(Long id) {
		return entityManager.find(Client.class, id);
	}

	public void update(Client client) {
		entityManager.merge(client);
	}

	public void delete(Client client) {
		client = entityManager.merge(client);
		entityManager.remove(client);
	}

	public Collection<Client> readAll() {
		return (List<Client>) entityManager.createNamedQuery("CLIENTES.getAll").getResultList();
	}

	public Client getByName(String nombre) {
		return (Client) entityManager.createNamedQuery("CLIENTES.searchName").setParameter("nombre", nombre)
				.getResultList();
	}

}
