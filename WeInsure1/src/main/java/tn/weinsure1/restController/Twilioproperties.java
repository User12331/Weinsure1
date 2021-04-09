package tn.weinsure1.restController;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
public class Twilioproperties {
	
	public static final String accountSid="AC6b77f975bd8d990436ea4376ab5ad941";
	public static final String authToken="d9ce8a8debead8e06e6535764aaab8a0";
	public static final String serviceId="VA1bad8f23a3952afc8e5845a5fe8a02c8";
	
	public Twilioproperties() {
		
	}
	
	public String getAccountSid() {
		return accountSid;
	}
	
	public String getAuthToken() {
		return authToken;
	}
	
	public String getServiceId() {
		return serviceId;
	}
	

}
