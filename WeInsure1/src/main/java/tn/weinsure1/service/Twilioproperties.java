package tn.weinsure1.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
public class Twilioproperties {
 	private String accountSid = "AC6b77f975bd8d990436ea4376ab5ad941";
	private String authToken = "1899cf978d47096bbf35c9af5c7c8cd7";
	private String fromNumber = "+13109974584";
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getFromNumber() {
		return fromNumber;
	}
	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}
	public Twilioproperties() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
