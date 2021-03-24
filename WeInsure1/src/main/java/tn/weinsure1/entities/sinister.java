package tn.weinsure1.entities;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="Sinistre")
public class sinister implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="IdSinistre")
	private Long idSinistre ;
	
    @Enumerated(EnumType.STRING)
    @Column(name="TypeSinistre")
	typeSinister typeSinistre;
     
	private String description;
	@Temporal(TemporalType.DATE)
    @Column(name="DateOccurence")
	private Date dateOccurence;
	
	@Enumerated(EnumType.STRING)
    @Column(name="Status")
	private sinisterstatus status;
	
	@Column(name="Documents")
	private File documents ;



	
	public Long getIdSinistre() {
		return idSinistre;
	}

	public void setIdSinistre(Long idSinistre) {
		this.idSinistre = idSinistre;
	}

	public typeSinister getTypeSinistre() {
		return typeSinistre;
	}

	public void setTypeSinistre(typeSinister typeSinistre) {
		this.typeSinistre = typeSinistre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateOccurence() {
		return dateOccurence;
	}

	public void setDateOccurence(Date dateOccurence) {
		this.dateOccurence = dateOccurence;
	}

	public sinisterstatus getStatus() {
		return status;
	}

	public void setStatus(sinisterstatus status) {
		this.status = status;
	}

	public File getDocuments() {
		return documents;
	}

	public void setDocuments(File documents) {
		this.documents = documents;
	}

	@Override
	public String toString() {
		return "Sinistre [idSinistre=" + idSinistre + ", typeSinistre=" + typeSinistre + ", description=" + description
				+ ", dateOccurence=" + dateOccurence + ", status=" + status + ", documents=" + documents + "]";
	}

	public sinister(Long idSinistre, tn.weinsure1.entities.typeSinister typeSinistre, String description,
			Date dateOccurence, sinisterstatus status, File documents) {
		super();
		this.idSinistre = idSinistre;
		this.typeSinistre = typeSinistre;
		this.description = description;
		this.dateOccurence = dateOccurence;
		this.status = status;
		this.documents = documents;
	}

	public sinister() {
		super();
	}

	public sinister(tn.weinsure1.entities.typeSinister typeSinistre, String description, Date dateOccurence,
			sinisterstatus status, File documents) {
		super();
		this.typeSinistre = typeSinistre;
		this.description = description;
		this.dateOccurence = dateOccurence;
		this.status = status;
		this.documents = documents;
	}

	
	
	


}