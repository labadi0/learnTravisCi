package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import entity.Consultation;
import entity.Transfer;
import services.ConsultationService;

@RestController
public class ConsultationController {
	@Autowired
	private ConsultationService consultationService;

	@RequestMapping(method = RequestMethod.GET, value = "consultations/{id}")
	public Object getConsultationById(@PathVariable int id) {
		if  (consultationService.getConsultationById(id) != null) {
			return consultationService.getConsultationById(id); 
		}
		else {
			return "this consultation does not exist";
          //  throw new ResponseStatusException(HttpStatus.NOT_FOUND);	
		
		}
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/consultations")
	public List<Consultation> getAllConsultations() {
		return consultationService.getAllConsultation();
	}

	@PostMapping(value = "/consultations")
	public void saveConsultation(@RequestBody Consultation consultation) {
		consultationService.saveConsultation(consultation);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/AllConsultations")
	public List<Consultation> saveAllConsultations(@RequestBody List<Consultation> consultations) {
		return consultationService.saveAllConsultation(consultations);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/consultations")
	public Consultation updateConsultation(@RequestBody Consultation consultation) {
		return consultationService.updateConsultationById(consultation);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/consultations/{id}")
	public String deleteConsultation(@PathVariable int id) {
		return consultationService.deleteConsultation(id);
	}

}
