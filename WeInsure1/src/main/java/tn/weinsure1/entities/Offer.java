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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Column(name="score_min")
	private int score_min;
	@Column(name="score_max")
	private int score_max;
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

	public int getScore_min() {
		return score_min;
	}
	public void setScore_min(int score_min) {
		this.score_min = score_min;
	}
	public int getScore_max() {
		return score_max;
	}
	public void setScore_max(int score_max) {
		this.score_max = score_max;
	}

	@ManyToOne
	 @JoinColumn(name="IdUser")
	@JsonIgnore
	 private User user;
	
	 

	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Offer(Long idOffer, String type, Date expiration_date, String description, int scoremin, int scoremax) {
		super();
		IdOffer = idOffer;
		Type = type;
		Expiration_date = expiration_date;
		Description = description;
		score_min = scoremin;
		score_max = scoremax;
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