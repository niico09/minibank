package minibank.bbva.model.entitys;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movements {

	@Id
	private Long id;

	private Date dayTransfer;

	private double amount;

	private String description;

	private String typeMoney;

	private String origin;

	private String destination;

	private String site;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDayTransfer() {
		return dayTransfer;
	}

	public void setDayTransfer(Date dayTransfer) {
		this.dayTransfer = dayTransfer;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeMoney() {
		return typeMoney;
	}

	public void setTypeMoney(String typeMoney) {
		this.typeMoney = typeMoney;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
