package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import entity.Transfer;
import services.TransferService;

@RestController
public class TransferController {
	@Autowired
	private TransferService transferService;
	
	
	@RequestMapping(method = RequestMethod.GET,value = "transfers/{id}")
	public Transfer getTransferById(@PathVariable int id) {
		return transferService.getTransferById(id);
	}
	@RequestMapping(method = RequestMethod.GET,value = "/transfers")
	public List<Transfer> getAllTransfers(){
		return transferService.getAllTransfers();
	}
	/*
	@RequestMapping(method = RequestMethod.POST,value = "/transfers")
	public Transfer saveTransfer(@RequestBody Transfer transfer) {
		return transferService.saveTransfer(transfer);
	}
	*/
	@PostMapping(value = "/transfers")
	public Transfer saveTransfer(@RequestBody	 Transfer transfer) {
		return transferService.saveTransfer(transfer);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/transfersAll")
	public List<Transfer> saveAllTransfers(@RequestBody List<Transfer> transfers){
		return transferService.saveAllTransfers(transfers);
	}
	
	@RequestMapping(method = RequestMethod.PUT , value = "/transfers")
	public Transfer updateTransfer( @RequestBody Transfer transfer) {
		return transferService.updateTransfer(transfer);
	}
	@RequestMapping(method = RequestMethod.DELETE,value = "/transfers/{id}")
	public String deleteTrasfer(@PathVariable int id) {
		return transferService.deleteTransfer(id);
	}
	
	
	
}
