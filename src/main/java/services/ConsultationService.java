package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ConsultationDao;
import entity.Consultation;
import lombok.Data;

@Service
@Data
public class ConsultationService {
	@Autowired
	private ConsultationDao consultationDao;
	
	public Consultation saveConsultation(Consultation consultation) {
		return consultationDao.save(consultation);
	}

	public List<Consultation> saveAllConsultation(List<Consultation> consultations) {
		return consultationDao.saveAll(consultations);
	}

	public Consultation getConsultationById(int id) {
		return consultationDao.findById(id).orElse(null);
	}

	public List<Consultation> getAllConsultation() {
		return consultationDao.findAll();
	}

	public Consultation updateConsultationById(Consultation consultation) {
		Consultation existingConsultation = consultationDao.findById(consultation.getId()).orElse(null);
		existingConsultation.setNumberConsultation(consultation.getNumberConsultation());
		return consultationDao.save(existingConsultation);
	}

	public String deleteConsultation(int id) {
		consultationDao.deleteById(id);
		return "consulataion removed !! " + id;
	}
	
}
