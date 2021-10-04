package controllers;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tomcat.util.json.JSONParser;
//import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Client;
import entity.Consultation;
import entity.Operation;
import entity.Transfer;
import services.ClientService;
import services.ConsultationService;
import services.TransferService;

@RestController
public class ClientController {
	@Autowired
	private ClientService clientService;


	@RequestMapping(method = RequestMethod.GET, value = "clients/{id}")
	public Client getClientById(@PathVariable int id) {
		return clientService.getClientById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/clients")
	public List<Client> getAllClients() {
		return clientService.getAllClient();
	}

	@PostMapping(value = "/clients")

	public void saveClient(  @RequestBody Client client) {
		
		//if (type.equals("transfer")) {
		//	System.err.println("helllollll");
		//	if (client.getOperation() instanceof Transfer ) {
		//	System.err.println("helo");
		//		System.out.println(client.toString());
				clientService.saveClient(client);
		//	}
			
			
		//}
		/*
		JSONObject j2 = new JSONObject(x);
		JSONObject jsonObject = j2.getJSONObject("operation");

		Set<String> setKeys = jsonObject.keySet();
		Map<String, Object> yourMap = new HashMap<>();
		for (String key : setKeys) {
			yourMap.put(key, jsonObject.get(key));
		}

		int idClient = j2.getInt("id");
		String name = j2.getString("name");
		String rib = j2.getString("rib");
		int amount = j2.getInt("amount");

//		System.out.println(Arrays.asList(yourMap)); // method 1
		String operationName = (String) yourMap.get("operationName");
		if (operationName.equals("transfer")) {
			int id_transfer = Integer.parseInt(yourMap.get("id").toString());
			double transferMoney = (double) Integer.parseInt(yourMap.get("moneyTransfer").toString());
			Client client = new Client(idClient, name, rib, amount,new Transfer(id_transfer, operationName, transferMoney));
			clientService.saveClient(client);

		} else if (operationName.equals("consultation")) {

			int id_consultation = Integer.parseInt(yourMap.get("id").toString());
			int numberConsultation = Integer.parseInt(yourMap.get("numberConsultation").toString());

			Client client = new Client(idClient, name, rib, amount,new Consultation(id_consultation, operationName, numberConsultation));
			clientService.saveClient(client);

		}
		*/

//		System.out.println(operationName);
//		System.out.println(id_transfer);
//		System.out.println(transferMoney);

		// x.setOperation(new Transfer(id_transfer, operationName,transferMoney));

		// clientService.saveClient(x);

//		ObjectMapper mapper = new ObjectMapper();
//		TypeReference<Transfer> typeReference = new TypeReference<Transfer>(){};
//		InputStream inputStream = TypeReference.class.getResourceAsStream(x);
//		try {
//			Transfer users = mapper.readValue(inputStream,typeReference);
//			userService.save(users);
//			System.out.println("Users Saved!");
//		} catch (IOException e){
//			System.out.println("Unable to save users: " + e.getMessage());
//		}
//		

//		if (type.equals("transfer")) {
//			clientService.saveClient(client);
//			
////			Transfer t = new Transfer();
////			Operation o = client.getOperation();
////			BeanUtils.copyProperties(o,t);
////			transferService.saveTransfer(t);
//			
//			
//			//Transfer t = client.getOperation()
//			
//			//transferService.saveTransfer((Transfer)client.getOperation());			
//		}
//	
//		else {
//			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST);	
//		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/allClients")
	public void saveAllClient(@RequestBody List<Client> clients) {
		clientService.saveAllClients(clients);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/clients")
	public Client updateClient(@RequestBody Client client) {
		return clientService.updateClientById(client);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/clients/{id}")
	public String deleteClient(@PathVariable int id) {
		return clientService.deleteClient(id);
	}

}
