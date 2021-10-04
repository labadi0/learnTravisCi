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

import entity.Client;
import entity.Consultation;
import entity.Transfer;
import services.ClientService;
import services.ConsultationService;

@SpringBootTest(classes = com.example.demo.BankRestApplication.class)
@AutoConfigureMockMvc
public class ClientTestController {
	
	@MockBean
	private ClientService service;

	@Autowired
	private MockMvc mockMvc;
	@Test
	@DisplayName("GET /clients/1")
	void getClientTestController() throws Exception {
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		Mockito.when(service.getClientById(1)).thenReturn(client);
		mockMvc.perform(get("/clients/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("client1")))
				.andExpect(jsonPath("$.rib", is("12AEZR1A")));

	}
	
	
	
	
	@Test
	@DisplayName("GET /clients")
	void testgetAllTransfersControllers() throws Exception {
		Client client1 = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		Client client2 = new Client(2, "client2", "12AEZRFJJKLUJ1A", 300, new Transfer(2, 300));
		doReturn(Lists.newArrayList(client1, client2)).when(service).getAllClient();
		mockMvc.perform(get("/clients"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("client1")))
				.andExpect(jsonPath("$[0].rib", is("12AEZR1A")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("client2")))
				.andExpect(jsonPath("$[1].rib", is("12AEZRFJJKLUJ1A")));

	}
	
	@Test
	@DisplayName("POST /clients")
	void saveClientTestcontroller() throws Exception {
		// Setup our mocked service
		Client clientToPost = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		Client clientToReturn = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		doReturn(clientToReturn).when(service).saveClient(any());
		mockMvc.perform(post("/clients")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(clientToPost)))
				.andExpect(status().isOk());

	}
	
	
	@Test	  
	@DisplayName("POST /allClients")
	void createAllClientTestController() throws Exception { 	
		Client client1 = new Client(1, "floyd", "12T12235fdkslmfksDQSDQDQ23", 450, new Consultation(1, 300));
		Client client2 = new Client(2, "sat", "djqksldjksldfjksdfjksdl", 450, new Transfer(1, 300));
		ArrayList<Client> array =new ArrayList<>();
		array.add(client1); 
		array.add(client2);
		Mockito.when(service.saveAllClients(any())).thenReturn(array);
		String json = "[\r\n"
				+ "    {\r\n"
				+ "        \r\n"
				+ "        \"name\": \"floyd\",\r\n"
				+ "        \"rib\": \"12T12235fdkslmfksDQSDQDQ23\",\r\n"
				+ "        \"amount\": 450,\r\n"
				+ "        \"operation\": {\r\n"
				+ "            \"operationName\": \"consultation\",\r\n"
				+ "            \"numberConsultation\": 300\r\n"
				+ "        }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \r\n"
				+ "        \"name\": \"sat\",\r\n"
				+ "        \"rib\": \"djqksldjksldfjksdfjksdl\",\r\n"
				+ "        \"amount\": 450,\r\n"
				+ "        \"operation\": {\r\n"
				+ "            \r\n"
				+ "            \"operationName\": \"transfer\",\r\n"
				+ "            \"moneyTransfer\": 300\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "]";
				mockMvc.perform(post("/allClients")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
						.andExpect(status().isOk());
	  }
	
	
	@Test
	@DisplayName("PUT /clients")
	void updateClientControlller() throws Exception {
		// Setup our mocked service
		Client clientToPut = new Client(1, "floyd", "12T12235fdkslmfksDQSDQDQ23", 450, new Consultation(1, 300));
		Client clientToReturn = new Client(1, "ghost", "12T12235fdkslmfksDQSDQDQ23", 550, new Consultation(1, 300));
		doReturn((clientToReturn)).when(service).getClientById(1);
		doReturn(clientToReturn).when(service).saveClient(any());
		doReturn(clientToPut).when(service).updateClientById(clientToPut);
		mockMvc.perform(put("/clients")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(clientToPut)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("floyd")))
				.andExpect(jsonPath("$.amount", is(450.0)));
	}
	
	@Test
	@DisplayName("delete /clients")
	void deleteTransferControlller() throws Exception {
		Client client = new Client(1, "floyd", "12T12235fdkslmfksDQSDQDQ23", 450, new Consultation(1, 300));
		Mockito.when(service.getClientById(1)).thenReturn(client);
		this.mockMvc.perform(delete("/clients/{id}", 1))
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
