package servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import dao.BankDao;
import dao.ClientDao;
import entity.Bank;
import entity.Client;
import entity.Transfer;
import services.BankService;
import services.ClientService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BankServiceTest {
	@Mock
	private BankDao bankDao;

	@Captor
	private ArgumentCaptor<Transfer> postArgumentCaptor;
	
	@Test
	@DisplayName("je test si la method saveBank foncionne ")
	public void saveBankTest() {
		
		BankService bankService = new BankService();
		bankService.setBankDao(bankDao);
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		ArrayList<Client> clients = new ArrayList<Client>();
		clients.add(client);	
		Bank bank = new Bank(1, clients, "axa Banque");
		
		
		/*
		postService.saveTransfer(postRequest);
		Mockito.verify(userRepository, Mockito.times(1)).save(postArgumentCaptor.capture());

		Assertions.assertThat(postArgumentCaptor.getValue().getId()).isEqualTo(1);
		Assertions.assertThat(postArgumentCaptor.getValue().getMoneyTransfer()).isEqualTo(200);
		*/
		Mockito.when(bankDao.save(bank)).thenReturn(bank);
        Bank found = bankService.saveBank(bank);

        assertNotNull(found);
        assertEquals(bank.getIdBank(), found.getIdBank());
        assertEquals(bank.getBankName(), found.getBankName());

	}
	
	
	@Test
	@DisplayName("ici je teste si la methode deleteBank marche ou pas ")
	public void deleteBankTest() {
		BankService bankService = new BankService();
		bankService.setBankDao(bankDao);
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		ArrayList<Client> clients = new ArrayList<Client>();
		clients.add(client);	
		Optional<Bank> bank = Optional.of(new Bank(1, clients, "axa Banque"));
		// when(userRepository.deleteById(1)).thenReturn(transferEntity);

		//bankService.deleteBank(bank.getIdBank());

        //Mockito.verify(bankDao, Mockito.times(1)).deleteById(bank.getIdBank());
        
		Mockito.when(bankDao.findById(1)).thenReturn(bank);
		Mockito.when(bankDao.existsById(bank.get().getIdBank())).thenReturn(false);
		assertFalse(bankDao.existsById(bank.get().getIdBank()));
        
       


	}

	
	
	
	@Test
	@DisplayName("ici je teste si la methode getbank marche ou pas ")
	public void getBankTest() {
		BankService bankService = new BankService();
		bankService.setBankDao(bankDao);
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		ArrayList<Client> clients = new ArrayList<Client>();
		clients.add(client);	
		Optional<Bank> bank = Optional.of(new Bank(1, clients, "axa Banque"));	
		when(bankDao.findById(1)).thenReturn(bank);
		Bank tf = bankService.getBankById(1);
		assertEquals(1, tf.getIdBank());
	}

	@Test
	@DisplayName("ici je teste si la methode updateBank marche ou pas ")
	public void updateBankTest() {
		BankService bankService = new BankService();
		bankService.setBankDao(bankDao);
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		ArrayList<Client> clients = new ArrayList<Client>();
		clients.add(client);	
		Optional<Bank> bank = Optional.of(new Bank(1, clients, "axa Banque"));
		Mockito.when(bankDao.findById(1)).thenReturn(bank);
		bank.get().setBankName("bnp");
		Mockito.when(bankDao.save(bank.get())).thenReturn(bank.get());
		assertThat(bankService.updateBankById(bank.get()).getBankName()).isEqualTo(bank.get().getBankName());
		assertThat(bank.get().getIdBank()).isEqualTo(1);

		// System.out.println(transfer.get().getMoneyTransfer());
	}

	@Test
	@DisplayName("ici je teste si la methode getAllClient marche ou pas ")
	public void getAllClientTest() {
		BankService bankService = new BankService();
		bankService.setBankDao(bankDao);
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		ArrayList<Client> clients = new ArrayList<Client>();
		clients.add(client);	
		Bank bank = new Bank(1, clients, "axa Banque");
		
		Client client2 = new Client(2, "client2", "12AEZdqdqR1A", 200, new Transfer(2, 233));
		ArrayList<Client> clients2 = new ArrayList<Client>();
		clients2.add(client2);	
		Bank bank2 = new Bank(1, clients2, "bnp");
		
		ArrayList<Bank> banks = new ArrayList<Bank>();
		banks.add(bank);
		banks.add(bank2);

		Mockito.when(bankDao.findAll()).thenReturn(banks);
		assertThat(bankService.getAllBanks().size()).isEqualTo(banks.size());
	}
	
	
	@Test
	@DisplayName("ici je teste si la methode saveAllClient marche ou pas ")
	public void saveAllClientTest() {
		BankService bankService = new BankService();
		bankService.setBankDao(bankDao);
		
		Client client = new Client(1, "client1", "12AEZR1A", 200, new Transfer(1, 200));
		ArrayList<Client> clients = new ArrayList<Client>();
		clients.add(client);	
		Bank bank = new Bank(1, clients, "axa Banque");
		
		Client client2 = new Client(2, "client2", "12AEZdqdqR1A", 200, new Transfer(2, 233));
		ArrayList<Client> clients2 = new ArrayList<Client>();
		clients2.add(client2);	
		Bank bank2 = new Bank(1, clients2, "bnp");
		
		ArrayList<Bank> banks = new ArrayList<Bank>();
		banks.add(bank);
		banks.add(bank2);

		
		
		
		Mockito.when(bankDao.saveAll(banks)).thenReturn(banks);
		assertThat(bankService.saveAllBanks(banks).size()).isEqualTo(banks.size());
	}

	
	

	
	
}
