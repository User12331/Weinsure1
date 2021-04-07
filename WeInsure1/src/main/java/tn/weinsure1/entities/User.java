package tn.weinsure1.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User  implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdUser")
	private Long id;
	@NotBlank
	@Size(max = 20)
	private String username;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Birthdate")
	private Date birthdate ;
	
	
	@Column(name="Phonenumber")
	private Long phonenumber ;
	
	@Column(name="Cin")
	private Long cin ;
	
	@Column(name="Salary")
	private Float salary ;


///////////////////////////////////////////////////////////////////
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
////////////////////////////////////////////////////////////////////
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy="user", cascade = CascadeType.ALL)
	private List<sinister> sinisterList = new ArrayList<>();

/////////////////////////////////////////////////////////////////
@OneToMany(mappedBy="user")
private Set<Contract> contracts;
////////////////////////////////////////////////////////////
@OneToMany(mappedBy="user")
private  Set<Offer> offers ;
////////////////////////////////////////////////////////////////


	@JsonIgnore
	//@JsonBackReference
	@OneToOne
	@JoinColumn(name = "idcontraint",referencedColumnName="IDContraint")
	private Contraint contraint;



	public User(Set<Offer> offers) {
		super();
		this.offers = offers;
	}

	
public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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

	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public List<sinister> getSinisterList() {
		return sinisterList;
	}
	public void setSinisterList(List<sinister> sinisterList) {
		this.sinisterList = sinisterList;
	}
	public Set<Contract> getContracts() {
		return contracts;
	}
	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}
	public Set<Offer> getOffers() {
		return offers;
	}
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	/**
	 * @param id
	 * @param username
	 * @param email
	 * @param password
	 * @param birthdate
	 * @param phonenumber
	 * @param cin
	 * @param salary
	 * @param role
	 * @param roles
	 * @param sinisterList
	 * @param contracts
	 * @param offers
	 */
	public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password, Date birthdate, Long phonenumber, Long cin, Float salary,
		 Set<Role> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.birthdate = birthdate;
		this.phonenumber = phonenumber;
		this.cin = cin;
		this.salary = salary;
		this.roles = roles;

	}

	
	/**
	 * @param username
	 * @param email
	 * @param password
	 * @param birthdate
	 * @param phonenumber
	 * @param cin
	 * @param salary
	 * @param roles
	 */
	public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password, Date birthdate, Long phonenumber, Long cin, Float salary,
			Set<Role> roles) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.birthdate = birthdate;
		this.phonenumber = phonenumber;
		this.cin = cin;
		this.salary = salary;
		this.roles = roles;
	}
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	public User(){
		super();
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", birthdate=" + birthdate + ", phonenumber=" + phonenumber + ", cin=" + cin + ", salary=" + salary
				+  ", roles=" + roles +  "]";
	}
	
}
	
