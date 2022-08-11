package minibank.bbva.model.dto;

import minibank.bbva.model.entitys.Address;

public class ClientAddressDTO {

	private Long id;
	private Address newAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(Address newAddress) {
		this.newAddress = newAddress;
	}

}
