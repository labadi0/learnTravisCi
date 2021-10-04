package ControllersTest;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import entity.Transfer;
import services.TransferService;

@SpringBootTest(classes = com.example.demo.BankRestApplication.class)
@AutoConfigureMockMvc
public class TransferTestController {
	@MockBean
	private TransferService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("GET /transfers/1")
	void getTransferTestController() throws Exception {
		Transfer transfer = new Transfer(1, 200);
		Mockito.when(service.getTransferById(1)).thenReturn(transfer);
		mockMvc.perform(get("/transfers/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.moneyTransfer", is(200.0)))
				.andExpect(jsonPath("$.operationName", is("transfer")));

	}

	@Test
	@DisplayName("GET /Transfers")
	void testgetAllTransfersControllers() throws Exception {
		Transfer transfer1 = new Transfer(1, 200);
		Transfer transfer2 = new Transfer(2, 300);
		doReturn(Lists.newArrayList(transfer1, transfer2)).when(service).getAllTransfers();
		mockMvc.perform(get("/transfers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].moneyTransfer", is(200.0)))
				.andExpect(jsonPath("$[0].operationName", is("transfer")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].moneyTransfer", is(300.0)))
				.andExpect(jsonPath("$[1].operationName", is("transfer")));

	}

	@Test
	@DisplayName("POST /transfers")
	void saveTransferTestcontroller() throws Exception {
		// Setup our mocked service
		Transfer TransferToPost = new Transfer(1, 200);
		Transfer TransferToReturn = new Transfer(1, 200);
		doReturn(TransferToReturn).when(service).saveTransfer(any());
		mockMvc.perform(post("/transfers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(TransferToPost)))
				.andExpect(status().isOk()) 
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.moneyTransfer", is(200.0)))
				.andExpect(jsonPath("$.operationName", is("transfer")));

	}

	@Test	  
	  @DisplayName("POST /transfersAll")
	void createAllTransferTestController() throws Exception { 	
		Transfer transfer1 = new Transfer(1,200);
		Transfer transfer2 = new Transfer(2, 300) ;
		ArrayList<Transfer> array =new ArrayList<>();
		array.add(transfer1); 
		array.add(transfer2);
		Mockito.when(service.saveAllTransfers(any())).thenReturn(array);
		String json = "[\r\n"
				+ "    {\r\n"
				+ "        \"id\": 2,\r\n"
				+ "        \"operationName\": \"transfer\",\r\n"
				+ "        \"moneyTransfer\": 250.0\r\n"
				+ "    },\r\n"
				+ "        {\r\n"
				+ "        \"id\": 3,\r\n"
				+ "        \"operationName\": \"transfer\",\r\n"
				+ "        \"moneyTransfer\": 300.0\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "]";
				mockMvc.perform(post("/transfersAll")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
						.andExpect(status().isOk());
	  }

	@Test
	@DisplayName("PUT /transfers")
	void updateTransferControlller() throws Exception {
		// Setup our mocked service
		Transfer transferToPut = new Transfer(1, 200);
		Transfer transferToReturn = new Transfer(1, 300);
		doReturn((transferToReturn)).when(service).getTransferById(1);
		doReturn(transferToReturn).when(service).saveTransfer(any());
		doReturn(transferToPut).when(service).updateTransfer(transferToPut);
		mockMvc.perform(put("/transfers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(transferToPut)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.moneyTransfer", is(200.0)))
				.andExpect(jsonPath("$.operationName", is("transfer")));
	}

	@Test
	@DisplayName("delete /transfers")
	void deleteTransferControlller() throws Exception {
		Transfer transfer = new Transfer(1, 200);
		Mockito.when(service.getTransferById(1)).thenReturn(transfer);
		this.mockMvc.perform(delete("/transfers/{id}", 1))
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
