package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ClientDao;
import entity.Client;
import lombok.Data;

@Service
@Data
public class ClientService {
	@Autowired
	private ClientDao clientDao;
	
	
	public Client saveClient(Client client) {
		return clientDao.save(client);
	}

	public List<Client> saveAllClients(List<Client> clients) {
		return clientDao.saveAll(clients);
	}

	public Client getClientById(int id) {
		return clientDao.findById(id).orElse(null);
	}

	public List<Client> getAllClient() {
		return clientDao.findAll();
	}

	public Client updateClientById(Client client) {
		Client existingClient = clientDao.findById(client.getId()).orElse(null);
		existingClient.setName(client.getName());
		existingClient.setRib(client.getRib());
		return clientDao.save(existingClient);
	}

	public String deleteClient(int id) {
		clientDao.deleteById(id);
		return "Client removed !! " + id;
	}
	

}
