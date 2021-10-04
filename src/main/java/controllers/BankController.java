package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import entity.Bank;
import entity.Client;
import services.BankService;

@RestController
public class BankController {
	@Autowired
	BankService bankService;

	@RequestMapping(method = RequestMethod.GET, value = "banks/{id}")
	public Bank getBankById(@PathVariable int id) {
		return bankService.getBankById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/banks")
	public List<Bank> getAllBanks() {
		return bankService.getAllBanks();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/banks")
	public Bank saveBank(@RequestBody Bank bank) {
		return bankService.saveBank(bank);

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/allbanks")
	public void saveAllBanks(@RequestBody List<Bank> banks) {
		bankService.saveAllBanks(banks);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/banks")
	public Bank updateBank(@RequestBody Bank bank) {
		return bankService.updateBankById(bank);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/banks/{id}")
	public String deleteBank(@PathVariable int id) {
		return bankService.deleteBank(id);
	}

}
