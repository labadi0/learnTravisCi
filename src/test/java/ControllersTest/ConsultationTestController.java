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

import entity.Consultation;
import entity.Transfer;
import services.ConsultationService;
import services.TransferService;

@SpringBootTest(classes = com.example.demo.BankRestApplication.class)
@AutoConfigureMockMvc
public class ConsultationTestController {

	@MockBean
	private ConsultationService service;

	@Autowired
	private MockMvc mockMvc;
	@Test
	@DisplayName("GET /consultation/1")
	void getConsultationTestController() throws Exception {
		Consultation consultation = new Consultation(1, 200);
		Mockito.when(service.getConsultationById(1)).thenReturn(consultation);
		mockMvc.perform(get("/consultations/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.numberConsultation", is(200)))
				.andExpect(jsonPath("$.operationName", is("consultation")));

	}
	
	
	@Test
	@DisplayName("GET /consultation")
	void testgetAllConsultationControllers() throws Exception {
		Consultation consultation1 = new Consultation(1, 200);
		Consultation consultation2 = new Consultation(2, 300);
		doReturn(Lists.newArrayList(consultation1, consultation2)).when(service).getAllConsultation();
		mockMvc.perform(get("/consultations"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].numberConsultation", is(200)))
				.andExpect(jsonPath("$[0].operationName", is("consultation")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].numberConsultation", is(300)))
				.andExpect(jsonPath("$[1].operationName", is("consultation")));

	}
	
	@Test
	@DisplayName("POST /consultations")
	void saveConsultationTestcontroller() throws Exception {
		// Setup our mocked service
		Consultation consultationToPost = new Consultation(1, 200);
		Consultation consultationToReturn = new Consultation(1, 200);
		doReturn(consultationToReturn).when(service).saveConsultation(any());
		mockMvc.perform(post("/consultations")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(consultationToPost)))
				.andExpect(status().isOk()) ;
	}
	
	@Test	  
	  @DisplayName("POST /AllConsultations")
	void createAllConsultationTestController() throws Exception { 	
		Consultation consultation1 = new Consultation(1,200);
		Consultation consultation2 = new Consultation(2, 300) ;
		ArrayList<Consultation> array =new ArrayList<>();
		array.add(consultation1); 
		array.add(consultation2);
		Mockito.when(service.saveAllConsultation(any())).thenReturn(array);
		String json = "[\r\n"
				+ "    {\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"operationName\": \"consultation\",\r\n"
				+ "        \"numberConsultation\": 200\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": 2,\r\n"
				+ "        \"operationName\": \"consultation\",\r\n"
				+ "        \"numberConsultation\": 300\r\n"
				+ "    }\r\n"
				+ "]";
				mockMvc.perform(post("/AllConsultations")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
						.andExpect(status().isOk());
	  }
	
	
	
	@Test
	@DisplayName("PUT /consultations")
	void updateConsultationControlller() throws Exception {
		// Setup our mocked service
		Consultation consultationToPut = new Consultation(1, 200);
		Consultation consultationToReturn = new Consultation(1, 300);
		doReturn((consultationToReturn)).when(service).getConsultationById(1);
		doReturn(consultationToReturn).when(service).saveConsultation(any());
		doReturn(consultationToPut).when(service).updateConsultationById(consultationToPut);
		mockMvc.perform(put("/consultations")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(consultationToPut)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.numberConsultation", is(200)))
				.andExpect(jsonPath("$.operationName", is("consultation")));
	}
	
	@Test
	@DisplayName("delete /consultations")
	void deleteTransferControlller() throws Exception {
		Consultation consultation = new Consultation(1, 200);
		Mockito.when(service.getConsultationById(1)).thenReturn(consultation);
		this.mockMvc.perform(delete("/consultations/{id}", 1))
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
