package tn.weinsure1.restController;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/insurer")
	@PreAuthorize("hasRole('INSURER')" )
	public String insurerAccess() {
		return "insurer Content.";
	}
	@GetMapping("/user")
	@PreAuthorize("hasRole('INSURED') or hasRole('INSURER') or hasRole('WOMEN')")
	public String userAccess() {
		return "user Content.";
	}
	@GetMapping("/insured")
	@PreAuthorize("hasRole('INSURED')")
	public String insuredAccess() {
		return "Insured content .";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin content .";
	}

		@GetMapping("/women")
		@PreAuthorize("hasRole('women')")
		public String womenAccess() {
			return "women content.";
	}
}
