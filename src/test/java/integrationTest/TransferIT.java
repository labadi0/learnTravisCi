package integrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Transfer;

@SpringBootTest(classes = com.example.demo.BankRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferIT {
	@LocalServerPort
	private int port;
	
	TestRestTemplate testRestTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void getTransferByidIT() throws JsonProcessingException {
		Transfer transfer = new Transfer(1, 200);

		
		String inputInJson = mapToJson(transfer);
		
		String URIToCreateTicket = "/transfers";
		HttpEntity<Transfer> entity = new HttpEntity<Transfer>(transfer, headers);
		testRestTemplate.exchange(formFullURLWithPort(URIToCreateTicket),
				HttpMethod.POST, entity, String.class);
	
		String URI = "/transfers/1";

	    String bodyJsonResponse = testRestTemplate.getForObject(formFullURLWithPort(URI), String.class);
		assertThat(bodyJsonResponse).isEqualTo(inputInJson);

	}
	
	
	
	@Test
	public void postTransferIT() throws Exception {
		Transfer transfer = new Transfer(1, 200);

		
		String URIToCreateTicket = "/transfers";
		
	    String inputInJson = this.mapToJson(transfer);
		
		HttpEntity<Transfer> entity = new HttpEntity<Transfer>(transfer, headers);
		ResponseEntity<String> response = testRestTemplate.exchange(
				formFullURLWithPort(URIToCreateTicket),
				HttpMethod.POST, entity, String.class);
		
		String responseInJson = response.getBody();
		assertThat(responseInJson).isEqualTo(inputInJson);
	}
	

	@Test
	public void getAllTransfer() throws JsonProcessingException {
		//String inputInJson = mapToJson(transfer);
		Transfer transfer = new Transfer(1, 200);

		String inputInJson = "[\r\n"
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
		
		
		String URIToCreateTicket = "/transfersAll";
		HttpEntity<Transfer> entity = new HttpEntity<Transfer>(transfer, headers);
		testRestTemplate.exchange(formFullURLWithPort(URIToCreateTicket),
				HttpMethod.POST, entity, String.class);
	
		String URI = "/transfers";

	    String bodyJsonResponse = testRestTemplate.getForObject(formFullURLWithPort(URI), String.class);
	    System.err.println(bodyJsonResponse);
		assertThat(bodyJsonResponse).isEqualTo(inputInJson);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * this utility method Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

    /**
     * This utility method to create the url for given uri. It appends the RANDOM_PORT generated port
     */
	private String formFullURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	
}
