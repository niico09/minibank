package minibank.bbva.model.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import minibank.bbva.model.dto.ClientAddressDTO;
import minibank.bbva.model.entitys.Client;
import minibank.bbva.service.ServiceClient;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ServiceClient serviceClient;

	@GetMapping("/")
	public List<Client> listar() {
		return serviceClient.listClient();
	}

//	@GetMapping(path = "/")
//	public CollectionModel<EntityModel<Client>> readAll() {
//
//		List<EntityModel<Client>> clientes = serviceClient.listClient().stream().map(this::mapping).toList();
//		Link self = linkTo(methodOn(ClientController.class).readAll()).withSelfRel();
//
//		return CollectionModel.of(clientes, self);
//	}

	private EntityModel<Client> mapping(Client cliente) {
		Link linkc = linkTo(methodOn(ClientController.class).searchById(cliente.getId())).withRel("cliente");
		return EntityModel.of(cliente, linkc);
	}

	@GetMapping("/name/{nombre}")
	public Client searchByName(@PathVariable String nombre) {
		return serviceClient.readByName(nombre);
	}

	@GetMapping("/buscarPorId/{idCliente}")
	public ResponseEntity<Client> searchById(@PathVariable Long id) {
		return new ResponseEntity<Client>(serviceClient.readById(id), HttpStatus.FOUND);
	}

	@PutMapping("/address")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void modificarDireccion(@RequestBody ClientAddressDTO clienteModifDireccionDTO) {
		serviceClient.chanceAddress(clienteModifDireccionDTO.getId(), clienteModifDireccionDTO.getNewAddress());
	}

}
