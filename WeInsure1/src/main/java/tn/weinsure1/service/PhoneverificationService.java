package tn.weinsure1.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

import tn.weinsure1.restController.Twilioproperties;




@Service("twilio")
public class PhoneverificationService {

	private final Twilioproperties twilioproperties;
	private static final Logger L = LogManager.getLogger(sinisterServiceImpl.class);
	@Autowired
	public PhoneverificationService(Twilioproperties twilioproperties) {
		this.twilioproperties=twilioproperties;
	}
	
	
	//method to send to otp
    public void startVerification(String phone) {
        try {
            Verification verification = Verification.creator(twilioproperties.getServiceId(), phone, "sms").create();
            if("approved".equals(verification.getStatus())|| "pending".equals(verification.getStatus())) {
			 	L.info("sinister +++ :" ) ;
			}
        } catch (ApiException exception) {
        	L.info("sinister +++ :" ) ;
        }

    }

    //mehtod to verifiy the otp
    public void checkverification(String phone, String code) {
        try {
            VerificationCheck verification = VerificationCheck.creator(twilioproperties.getServiceId(), code).setTo(phone).create();
            if("approved".equals(verification.getStatus())) {
            	L.info("sinister +++ :" ) ;
            }
            L.info("sinister +++ :" ) ;
        } catch (ApiException exception) {
        	L.info("sinister +++ :" ) ;
        }
    }
	
}

