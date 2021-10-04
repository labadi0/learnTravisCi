package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.*;
import entity.*;
import lombok.Data;

@Service
@Data
public class TransferService  {
	@Autowired
	private TransferDao transferDao;
	
	public Transfer saveTransfer(Transfer transfer) {
		return transferDao.save(transfer);
	}
	
	public List<Transfer> saveAllTransfers(List<Transfer> transfers) {
		return transferDao.saveAll(transfers);
	}
	
	public Transfer getTransferById(int id) {
		return transferDao.findById(id).orElse(null);
	}
	
	public List<Transfer> getAllTransfers() {
		return transferDao.findAll();
	}
	
	
	public Transfer updateTransfer(Transfer transfer) {
		Transfer existingTransfer = transferDao.findById(transfer.getId()).orElse(null);
		existingTransfer.setMoneyTransfer(transfer.getMoneyTransfer());
		return transferDao.save(existingTransfer);
	}
	
	public String deleteTransfer(int id) {
		transferDao.deleteById(id);
		return "Transfer removed !! " + id;
	}

}
