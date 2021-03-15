package tn.weinsure1.entities;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Contract")
public class Contract implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY )
	@Column(name="IDContract")
	private Long idcontract;
	@Column(name="Creation_Date")
	private Date Creation_date;
	@Column(name="Expiration_Date")
	private Date Expiration_date;
	@Column(name="Duration")
	private int Duration;
	@Column(name="Price")
	private float Price;
	@Column (name="Type")
	private String Type;
	@Column(name="Document")
	private File Document;
	public long getIdcontract() {
		return idcontract;
	}
	public void setIdcontract(Long idcontract) {
		this.idcontract = idcontract;
	}
	public Date getCreation_date() {
		return Creation_date;
	}
	public void setCreation_date(Date creation_date) {
		Creation_date = creation_date;
	}
	public Date getExpiration_date() {
		return Expiration_date;
	}
	public void setExpiration_date(Date expiration_date) {
		Expiration_date = expiration_date;
	}
	public int getDuration() {
		return Duration;
	}
	public void setDuration(int duration) {
		Duration = duration;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public File getDocument() {
		return Document;
	}
	public void setDocument(File document) {
		Document = document;
	}
	public Contract(Long idcontract, Date creation_date, Date expiration_date, int duration, float price, String type,
			File document) {
		super();
		this.idcontract = idcontract;
		this.Creation_date = creation_date;
		this.Expiration_date = expiration_date;
		this.Duration = duration;
		this.Price = price;
		this.Type = type;
		this.Document = document;
	}
	
	public Contract() {
		super();		
	}
	
	
}
