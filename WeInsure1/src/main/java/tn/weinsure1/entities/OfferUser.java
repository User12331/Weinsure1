package tn.weinsure1.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="OfferUser")

public class OfferUser implements Serializable{
	
	@Id
	@ManyToOne
	 @JoinColumn(name="IdUser", referencedColumnName="IdUser")
	 private User user;

	@Id
	@ManyToOne
	 @JoinColumn(name="IdOffer", referencedColumnName="IdOffer")
	 private Offer offer;
}
