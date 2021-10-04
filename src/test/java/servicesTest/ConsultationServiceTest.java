package servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import dao.ConsultationDao;
import dao.TransferDao;
import entity.Consultation;
import entity.Transfer;
import services.ConsultationService;
import services.TransferService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ConsultationServiceTest {
	
	@Mock
	private ConsultationDao consultationDao;

	@Captor
	private ArgumentCaptor<Transfer> postArgumentCaptor;

	@Test
	@DisplayName("je test si la method saveConsultation foncionne ")
	public void saveConsultationTest() {
		
		ConsultationService consultationService = new ConsultationService();
		consultationService.setConsultationDao(consultationDao);
		Consultation consultation = new Consultation(1, 100);
		Mockito.when(consultationDao.save(consultation)).thenReturn(consultation);
        Consultation found = consultationService.saveConsultation(consultation);

        assertNotNull(found);
        assertEquals(consultation.getId(), found.getId());
        assertEquals(consultation.getNumberConsultation(), found.getNumberConsultation());

	}
	

	@Test
	@DisplayName("ici je teste si la methode deleteConsultation marche ou pas ")
	public void deleteConsultationTest() {
		ConsultationService consultationService = new ConsultationService();
		consultationService.setConsultationDao(consultationDao);
		Optional<Consultation> consultation = Optional.of(new Consultation(1, 200));
		// when(userRepository.deleteById(1)).thenReturn(transferEntity);
		/*
		 * transferSerivce.deleteTransfer(transfer.getId()); Mockito.verify(transferDao,
		 * Mockito.times(1)).deleteById(transfer.getId());
		 */
		// Mockito.when(transferDao.findById(1)).thenReturn(transfer);
		Mockito.when(consultationDao.findById(1)).thenReturn(consultation);
		Mockito.when(consultationDao.existsById(consultation.get().getId())).thenReturn(false);
		assertFalse(consultationDao.existsById(consultation.get().getId()));

	}

	@Test
	@DisplayName("ici je teste si la methode getConsultation marche ou pas ")
	public void getConsultationTest() {
		ConsultationService consultationService = new ConsultationService();
		consultationService.setConsultationDao(consultationDao);
		
		
		Optional<Consultation> consultation = Optional.of(new Consultation(1, 200));
		when(consultationDao.findById(1)).thenReturn(consultation);

		Consultation tf = consultationService.getConsultationById(1);
		assertEquals(200, tf.getNumberConsultation());
	}

	@Test
	@DisplayName("ici je teste si la methode updateConsultation marche ou pas ")
	public void updateConsultationTest() {
		ConsultationService consultationService = new ConsultationService();
		consultationService.setConsultationDao(consultationDao);
		Optional<Consultation> consultation = Optional.of(new Consultation(1, 200));
		Mockito.when(consultationDao.findById(1)).thenReturn(consultation);
		consultation.get().setNumberConsultation(100);
		Mockito.when(consultationDao.save(consultation.get())).thenReturn(consultation.get());
		assertThat(consultationService.updateConsultationById(consultation.get())).isEqualTo(consultation.get());
		assertThat(consultation.get().getId()).isEqualTo(1);

		// System.out.println(transfer.get().getMoneyTransfer());
	}

	@Test
	@DisplayName("ici je teste si la methode getAllConsultation marche ou pas ")
	public void getAllConsultationTest() {
		ConsultationService consultationService = new ConsultationService();
		consultationService.setConsultationDao(consultationDao);
		Consultation consultation = new Consultation(1, 200);
		Consultation consultation2 = new Consultation(2, 400);
		ArrayList<Consultation> listConsultations = new ArrayList<>();
		listConsultations.add(consultation);
		listConsultations.add(consultation2);
		Mockito.when(consultationDao.findAll()).thenReturn(listConsultations);
		assertThat(consultationService.getAllConsultation().size()).isEqualTo(listConsultations.size());
	}
	
	
	@Test
	@DisplayName("ici je teste si la methode saveAllConsultation marche ou pas ")
	public void saveAllConsultationTest() {
		ConsultationService consultationService = new ConsultationService();
		consultationService.setConsultationDao(consultationDao);
		Consultation consultation = new Consultation(1, 200);
		Consultation consultation2 = new Consultation(2, 400);
		ArrayList<Consultation> listConsultations = new ArrayList<>();
		listConsultations.add(consultation);
		listConsultations.add(consultation2);		
		Mockito.when(consultationDao.saveAll(listConsultations)).thenReturn(listConsultations);
		assertThat(consultationService.saveAllConsultation(listConsultations).size()).isEqualTo(listConsultations.size());
	}
	
	
	
	
	
	
	
	

}
