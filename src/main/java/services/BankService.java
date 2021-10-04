package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BankDao;
import entity.*;
import lombok.Data;

@Service
public class BankService {
	@Autowired
	private BankDao bankDao;
	
	public Bank saveBank(Bank bank) {
		return bankDao.save(bank);
	}

	public List<Bank> saveAllBanks(List<Bank> banks) {
		return bankDao.saveAll(banks);
	}

	public Bank getBankById(int id) {
		return bankDao.findById(id).orElse(null);
	}

	public List<Bank> getAllBanks() {
		return bankDao.findAll();
	}

	public Bank updateBankById(Bank bank) {
		Bank existingBank = bankDao.findById(bank.getIdBank()).orElse(null);
		existingBank.setBankName(bank.getBankName());
		return bankDao.save(existingBank);
	}

	public String deleteBank(int id) {
		bankDao.deleteById(id);
		return "bank removed !! " + id;
	}

	public void setBankDao(BankDao bankDao) {
		this.bankDao = bankDao;
	}


	

}
