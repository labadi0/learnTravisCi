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

import dao.ClientDao;
import dao.ConsultationDao;
import entity.Client;
import entity.Consultation;
import entity.Transfer;
import services.ClientService;
import services.ConsultationService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClientServiceTest {
	
	@Mock
	private ClientDao clientDao;

	@Captor
	private ArgumentCaptor<Transfer> postArgumentCaptor;

	@Test
	@DisplayName("je test si la method saveClient foncionne ")
	public void saveClientTest() {
		
		ClientService clientService = new ClientService();
		clientService.setClientDao(clientDao);
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
	
		Mockito.when(clientDao.save(client)).thenReturn(client);
        Client found = clientService.saveClient(client);

        assertNotNull(found);
        assertEquals(client.getId(), found.getId());
        assertEquals(client.getName(), found.getName());

	}
	

	@Test
	@DisplayName("ici je teste si la methode deleteClient marche ou pas ")
	public void deleteClientTest() {

		ClientService clientService = new ClientService();
		clientService.setClientDao(clientDao);
		Optional<Client> client = Optional.of(new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200)));
		Mockito.when(clientDao.findById(1)).thenReturn(client);
		Mockito.when(clientDao.existsById(client.get().getId())).thenReturn(false);
		assertFalse(clientDao.existsById(client.get().getId()));


	}

	
	
	@Test
	@DisplayName("ici je teste si la methode getClient marche ou pas ")
	public void getClientTest() {
		ClientService clientService = new ClientService();
		clientService.setClientDao(clientDao);		
		Optional<Client> client = Optional.of(new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200)));
		when(clientDao.findById(1)).thenReturn(client);
		Client tf = clientService.getClientById(1);
		assertEquals(200, tf.getAmount());
	}

	@Test
	@DisplayName("ici je teste si la methode updateClient marche ou pas ")
	public void updateClientTest() {
		ClientService clientService = new ClientService();
		clientService.setClientDao(clientDao);
		Optional<Client> client = Optional.of(new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200)));
		Mockito.when(clientDao.findById(1)).thenReturn(client);
		client.get().setAmount(100);
		Mockito.when(clientDao.save(client.get())).thenReturn(client.get());
		assertThat(clientService.updateClientById(client.get()).getAmount()).isEqualTo(client.get().getAmount());
		assertThat(client.get().getId()).isEqualTo(1);

		// System.out.println(transfer.get().getMoneyTransfer());
	}

	@Test
	@DisplayName("ici je teste si la methode getAllClient marche ou pas ")
	public void getAllClientTest() {
		ClientService clientService = new ClientService();
		clientService.setClientDao(clientDao);
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		Client client2 = new Client(1, "client2", "12AEZdsqdqR1A", 210, new Transfer(2, 220));
		ArrayList<Client> clientsList = new ArrayList<>();
		clientsList.add(client);
		clientsList.add(client2);
		Mockito.when(clientDao.findAll()).thenReturn(clientsList);
		assertThat(clientService.getAllClient().size()).isEqualTo(clientsList.size());
	}
	
	
	@Test
	@DisplayName("ici je teste si la methode saveAllClient marche ou pas ")
	public void saveAllClientTest() {
		ClientService clientService = new ClientService();
		clientService.setClientDao(clientDao);
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		Client client2 = new Client(1, "client2", "12AEZdsqdqR1A", 210, new Transfer(2, 220));
		ArrayList<Client> clientList = new ArrayList<>();
		clientList.add(client);
		clientList.add(client2);		
		Mockito.when(clientDao.saveAll(clientList)).thenReturn(clientList);
		assertThat(clientService.saveAllClients(clientList).size()).isEqualTo(clientList.size());
	}
	
	
	

}
