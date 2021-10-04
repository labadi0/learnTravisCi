package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Consultation;

public interface ConsultationDao extends JpaRepository<Consultation, Integer> {
	
}
