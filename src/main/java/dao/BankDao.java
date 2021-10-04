package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entity.Bank;

public interface BankDao extends JpaRepository<Bank,Integer> {
	

	
}
