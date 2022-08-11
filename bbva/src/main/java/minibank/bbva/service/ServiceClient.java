package minibank.bbva.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

import minibank.bbva.model.dao.ClientDAO;
import minibank.bbva.model.entitys.Address;
import minibank.bbva.model.entitys.Client;

@Component
public class ServiceClient {

	@Resource(name = "clienteDAO")
	private ClientDAO clientDAO;

	@Transactional
	public void addClient(Client client) {
		clientDAO.create(client);
	}

	@Transactional
	public Client readById(Long id) {
		Client client = clientDAO.read(id);
		return client != null ? client : null;
	}

	@Transactional
	public List<Client> listClient() {
		return (List<Client>) clientDAO.readAll();
	}

	@Transactional
	public Client readByName(String nombre) {
		return (Client) clientDAO.getByName(nombre);
	}

	@Transactional
	public void chanceAddress(Long id, Address address) {
		Client client = clientDAO.read(id);

		if (client != null) {
			client.setDireccion(address);
			clientDAO.update(client);
		} else {
			throw new IllegalArgumentException("An error ocurred");
		}
	}

}
