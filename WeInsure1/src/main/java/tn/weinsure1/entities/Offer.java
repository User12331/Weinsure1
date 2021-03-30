package tn.weinsure1.entities;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Offer")
public class Offer implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY )
	@Column(name="IDOffer")
	private Long IdOffer;
	@Column (name="Type")
	private String Type;
	@Column(name="Expiration_Date")
	private Date Expiration_date;
	@Column(name="Description")
	private String Description;
	@Column(name="Document")
	private File Document;
	public Long getIdOffer() {
		return IdOffer;
	}
	public void setIdOffer(Long idOffer) {
		IdOffer = idOffer;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public Date getExpiration_date() {
		return Expiration_date;
	}
	public void setExpiration_date(Date expiration_date) {
		Expiration_date = expiration_date;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public File getDocument() {
		return Document;
	}
	public void setDocument(File document) {
		Document = document;
	}
	@ManyToOne
	 @JoinColumn(name="IdUser")
	 private User user;
	
	 
	/**
	 * @param idOffer
	 * @param type
	 * @param expiration_date
	 * @param description
	 * @param document
	 */
	public Offer(Long idOffer, String type, Date expiration_date, String description, File document) {
		super();
		IdOffer = idOffer;
		Type = type;
		Expiration_date = expiration_date;
		Description = description;
		Document = document;
	}
	/**
	 * 
	 */
	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
//test