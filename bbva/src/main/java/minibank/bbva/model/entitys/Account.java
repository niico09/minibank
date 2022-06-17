package minibank.bbva.model.entitys;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

	@Id
	private Long id;
	private Long number;
	private Long dniOwner;
	private Boolean primaryOwner;
	private Date createDate;
	private Double initalBalance;
	private Double actualBalance;
	private String agreed;
	private Date endDate;
	private String typeMoney;

	public Account() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Long getDniOwner() {
		return dniOwner;
	}

	public void setDniOwner(Long dniOwner) {
		this.dniOwner = dniOwner;
	}

	public Boolean getPrimaryOwner() {
		return primaryOwner;
	}

	public void setPrimaryOwner(Boolean primaryOwner) {
		this.primaryOwner = primaryOwner;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getInitalBalance() {
		return initalBalance;
	}

	public void setInitalBalance(Double initalBalance) {
		this.initalBalance = initalBalance;
	}

	public Double getActualBalance() {
		return actualBalance;
	}

	public void setActualBalance(Double actualBalance) {
		this.actualBalance = actualBalance;
	}

	public String getAgreed() {
		return agreed;
	}

	public void setAgreed(String agreed) {
		this.agreed = agreed;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTypeMoney() {
		return typeMoney;
	}

	public void setTypeMoney(String typeMoney) {
		this.typeMoney = typeMoney;
	}

}
