package tn.weinsure1.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="User")
public class User implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="IdUser")
	private Long idUser ;
	
	
	@Column(name="Lastname")
	private String lastname ;
	
	@Column(name="Firstname")
	private String firstname ;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Birthdate")
	private Date birthdate ;
	
	
	@Column(name="Login")
	private String login ;
	
	@Column(name="Password")
	private String paswword ;
	
	
	@Column(name="Phonenumber")
	private Long phonenumber ;
	
	@Column(name="Cin")
	private Long cin ;
	
	@Column(name="Salary")
	private Float salary ;

	@Enumerated(EnumType.STRING)
    @Column(name="Role")
	Role role;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="user", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("user")
	private List<sinister> sinisterList = new ArrayList<>();

	@OneToMany(mappedBy="user")
    private Set<Contract> contracts;
	
	@OneToMany(mappedBy="user")
	private  Set<Offer> offers ;
	
	


	public Set<Offer> getOffers() {
		return offers;
	}


	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}


	public User(Set<Offer> offers) {
		super();
		this.offers = offers;
	}

	
	public Set<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public List<sinister> getSinisterList() {
		return sinisterList;
	}

	public void setSinisterList(List<sinister> sinisterList) {
		this.sinisterList = sinisterList;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPaswword() {
		return paswword;
	}

	public void setPaswword(String paswword) {
		this.paswword = paswword;
	}

	public Long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(Long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Long getCin() {
		return cin;
	}

	public void setCin(Long cin) {
		this.cin = cin;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
	public User(Long idUser, String lastname, String firstname, Date birthdate, String login, String paswword,
			Long phonenumber, Long cin, Float salary, Role role) {
		super();
		this.idUser = idUser;
		this.lastname = lastname;
		this.firstname = firstname;
		this.birthdate = birthdate;
		this.login = login;
		this.paswword = paswword;
		this.phonenumber = phonenumber;
		this.cin = cin;
		this.salary = salary;
		
		this.role = role;
	}
	
	
	
	public User(String lastname, String firstname, Date birthdate, String login, String paswword, Long phonenumber,
			Long cin, Float salary, Role role, List<sinister> sinisterList) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.birthdate = birthdate;
		this.login = login;
		this.paswword = paswword;
		this.phonenumber = phonenumber;
		this.cin = cin;
		this.salary = salary;
		this.role = role;
		this.sinisterList = sinisterList;
	}

	public User( String lastname, String firstname, Date birthdate, String login, String paswword,
			long i, long j, float k, Role administrator) {

		this.lastname = lastname;
		this.firstname = firstname;
		this.birthdate = birthdate;
		this.login = login;
		this.paswword = paswword;
		this.phonenumber = i;
		this.cin = j;
		this.salary = k;
		this.role = administrator;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", lastname=" + lastname + ", firstname=" + firstname + ", birthdate="
				+ birthdate + ", login=" + login + ", paswword=" + paswword + ", phonenumber=" + phonenumber + ", cin="
				+ cin + ", salary=" + salary + ", role=" + role + "]";
	}


	
}