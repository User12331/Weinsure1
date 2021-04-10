package tn.weinsure1.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.weinsure1.entities.Offer;
import tn.weinsure1.service.IOfferService;

@RestController
public class OfferRestController {
	
	@Autowired
	IOfferService io;

	
}
