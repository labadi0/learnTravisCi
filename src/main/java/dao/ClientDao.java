package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Client;

public interface ClientDao extends JpaRepository<Client, Integer>{
	

}
