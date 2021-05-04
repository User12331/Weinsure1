package tn.weinsure1.controller;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.Offer;
import tn.weinsure1.entities.User;
import tn.weinsure1.service.IOfferService;

@Scope(value="session")
@Controller(value="OfferController")
@ELBeanName(value="OfferController")
@Join(path="/", to="/login.jsf")
public class OfferController {
	
	@Autowired 
	IOfferService io;
	
	private Long idoffer;
	private String type;
	private Date expiration_date;
	private String description;
	private float prix;
	private List<User> users;
	private List<Offer> offers;
	private Offer offer;

	public Offer getOffer() {
		return offer;
	}


	public String setOffer(Offer offer) {
		String navigateTo = "/update?faces-redirect=true";
		this.idoffer = offer.getIdOffer();
		this.type = offer.getType();
		this.expiration_date = offer.getExpiration_date();
		this.description = offer.getDescription();
		this.prix = offer.getPrix();
return navigateTo;
}
	
	public String setOffer2() {
		String navigateTo = "/topnum?faces-redirect=true";
		this.idoffer = null;
		this.type = null;
		this.expiration_date = null;
		this.description = null;
		this.prix = 0;
return navigateTo;
}
	
	public String gotoPack1() {
		String navigateTo = "/pack1?faces-redirect=true";
return navigateTo;
}
	
	public String gotoPack2() {
		String navigateTo = "/pack2?faces-redirect=true";
return navigateTo;
}
	
	public String gotoPack3() {
		String navigateTo = "/pack3?faces-redirect=true";
return navigateTo;
}
	
	public String gototop() {
		String navigateTo = "/top?faces-redirect=true";
return navigateTo;
}	

	public String gotofidele() {
		String navigateTo = "/fidele?faces-redirect=true";
return navigateTo;
}
	
	public String gotoold() {
		String navigateTo = "/old?faces-redirect=true";
return navigateTo;
}

	public Long getIdoffer() {
		return idoffer;
	}


	public void setIdoffer(Long idoffer) {
		this.idoffer = idoffer;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Date getExpiration_date() {
		return expiration_date;
	}


	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getPrix() {
		return prix;
	}


	public void setPrix(float prix) {
		this.prix = prix;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public List<Offer> getOffers() {
		return offers;
	}


	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}


	public String Hello(){
		return "helloe";
	}
	
	public Long topnum() {
	 Long o = io.topnum();
	 return o;
	 }
	
	public List<Long> topusers() {
		 List<Long> o = io.Top_Users(io.topnum());
		 return o;
		 }
	
	public List<Offer> retrieveoffer() {
		 List<Offer> o = io.retrieveAllOffers();
		 return o;
}
	
	public String addoffer2() {
		String navigateTo = "/table?faces-redirect=true"; 
		Offer o = new Offer(type,expiration_date,description,prix);
		io.addOffer1(o);
		return navigateTo;
	}
	
	public int test() {
		int test=13;
		 return test;
}
	
	public void delete(Long ido) {
		 io.deleteOffer(ido);
		 }
	
	public String updateOffer() {
		String navigateTo = "/table?faces-redirect=true"; 
		Offer o = new Offer(idoffer,type,expiration_date,description,prix);
		io.updateOffer(o);
		return navigateTo;
	}
	
public Offer findofferid(Long ido){
	return io.retrieveOffer(ido);
}

public List<User> pack1() {
	 List<User> o = io.pack1();
	 return o;
}

public List<User> Top2() {
	 return io.Top2();
}	

public int Top1(Long id) {
	 return io.Top1(id);
}

public List<User> fidele() {
	 return io.UserFidele();
}  

public Date old(Long id) {
	 return io.old(id);
}  

public List<User> old1() {
	 return io.old1();
}  

}
