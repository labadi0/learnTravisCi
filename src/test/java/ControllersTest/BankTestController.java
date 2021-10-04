package ControllersTest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Bank;
import entity.Client;
import entity.Consultation;
import entity.Transfer;
import services.BankService;
import services.ClientService;

@SpringBootTest(classes = com.example.demo.BankRestApplication.class)
@AutoConfigureMockMvc
public class BankTestController {
	@MockBean
	private BankService service;

	@Autowired
	private MockMvc mockMvc;
	@Test
	@DisplayName("GET /banks/1")
	void getBankTestController() throws Exception {
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		ArrayList<Client> clients = new ArrayList<Client>();
		clients.add(client);	
		Bank bank = new Bank(1, clients, "axa Banque");
		Mockito.when(service.getBankById(1)).thenReturn(bank);
		mockMvc.perform(get("/banks/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.idBank", is(1)))
				.andExpect(jsonPath("$.bankName", is("axa Banque")));

	}
	
	@Test
	@DisplayName("GET /banks")
	void testgetAllbanksControllers() throws Exception {
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		ArrayList<Client> clients = new ArrayList<Client>();
		clients.add(client);	
		Bank bank1 = new Bank(1, clients, "axa Banque");	
		
		Client client2 = new Client(2, "client2", "12AEZDSFDFSR1A", 300, new Consultation(1, 200));
		ArrayList<Client> clients2 = new ArrayList<Client>();
		clients2.add(client2);	
		Bank bank2 = new Bank(2, clients2, "bnp");
	
		doReturn(Lists.newArrayList(bank1, bank2)).when(service).getAllBanks();
		mockMvc.perform(get("/banks"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].idBank", is(1)))
				.andExpect(jsonPath("$[0].bankName", is("axa Banque")))
	
				.andExpect(jsonPath("$[1].idBank", is(2)))
				.andExpect(jsonPath("$[1].bankName", is("bnp")));

	}
	
	
	@Test
	@DisplayName("POST /banks")
	void saveBankTestcontroller() throws Exception {
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		ArrayList<Client> clients = new ArrayList<Client>();
		clients.add(client);	
		Bank bankToPost = new Bank(1, clients, "axa Banque");	
		Bank bankToReturn= new Bank(1, clients, "axa Banque");	
		
		
		doReturn(bankToReturn).when(service).saveBank(any());
		mockMvc.perform(post("/banks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bankToPost)))
				.andExpect(status().isOk());

	}
	
	@Test
	@DisplayName("PUT /clients")
	void updateClientControlller() throws Exception {
		// Setup our mocked service
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		ArrayList<Client> clients = new ArrayList<Client>();
		clients.add(client);	
		Bank bankToPut = new Bank(1, clients, "axa Banque");
		Bank bankToReturn = new Bank(1, clients, "bnp");

		
		
		//Client clientToPut = new Client(1, "floyd", "12T12235fdkslmfksDQSDQDQ23", 450, new Consultation(1, 300));
		//Client clientToReturn = new Client(1, "ghost", "12T12235fdkslmfksDQSDQDQ23", 550, new Consultation(1, 300));
		doReturn((bankToReturn)).when(service).getBankById(1);
		doReturn(bankToReturn).when(service).saveBank(any());
		doReturn(bankToPut).when(service).updateBankById(bankToPut);
		mockMvc.perform(put("/banks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bankToPut)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.idBank", is(1)))
				.andExpect(jsonPath("$.bankName", is("axa Banque")));
	}
	
	
	@Test
	@DisplayName("delete /clients")
	void deleteTransferControlller() throws Exception {
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		ArrayList<Client> clients = new ArrayList<Client>();
		clients.add(client);	
		Bank bank = new Bank(1, clients, "axa Banque");
		
		Mockito.when(service.getBankById(1)).thenReturn(bank);
		this.mockMvc.perform(delete("/banks/{id}", 1))
		.andExpect(status().isOk());
	}

	
	
	
	
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
}
