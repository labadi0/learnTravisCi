package servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import dao.TransferDao;
import entity.Transfer;
import services.TransferService;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransferServiceTest {

	@Mock
	private TransferDao transferDao;

	@Captor
	private ArgumentCaptor<Transfer> postArgumentCaptor;

	@Test
	@DisplayName("je test si la method saveTransfer foncionne ")
	public void saveTransferTest() {

		TransferService transferService = new TransferService();
		transferService.setTransferDao(transferDao);
		Transfer transfer = new Transfer(1, 200);
		Mockito.when(transferDao.save(transfer)).thenReturn(transfer);
		Transfer found = transferService.saveTransfer(transfer);
		assertNotNull(found);
		assertEquals(transfer.getId(), found.getId());
		assertEquals(transfer.getMoneyTransfer(), found.getMoneyTransfer());

	}

	/*
	 * @Test
	 * 
	 * @DisplayName("je test si la method updateTransfer foncionne ") public void
	 * UpdateTransferTest() throws Exception{
	 * 
	 * TransferService postService = new TransferService();
	 * postService.setTransferDao(userRepository); Optional<Transfer> postRequest =
	 * Optional.of(new Transfer(1, 200));
	 * 
	 * Transfer postRequest2 = new Transfer(1,100);
	 * 
	 * 
	 * Mockito.when(userRepository.save(postRequest.get())).thenReturn(postRequest.
	 * get()); Mockito.when(userRepository.findById(1)).thenReturn(postRequest);
	 * 
	 * Mockito.when(userRepository.save(postRequest2)).thenReturn(postRequest2);
	 * 
	 * Transfer found = postService.saveTransfer(postRequest.get());
	 * 
	 * Transfer found2 = postService.saveTransfer(postRequest2);
	 * 
	 * 
	 * assertNotNull(found2); assertEquals(found.getId(), found2.getId());
	 * 
	 * // assertEquals(100, found.getMoneyTransfer());
	 * 
	 * }
	 */

	@Test
	@DisplayName("ici je teste si la methode deleteTransfer marche ou pas ")
	public void deleteTransferTest() {
		TransferService transferSerivce = new TransferService();
		transferSerivce.setTransferDao(transferDao);
		Optional<Transfer> transfer = Optional.of(new Transfer(1, 200));
		// when(userRepository.deleteById(1)).thenReturn(transferEntity);
		/*
		 * transferSerivce.deleteTransfer(transfer.getId()); Mockito.verify(transferDao,
		 * Mockito.times(1)).deleteById(transfer.getId());
		 */
		// Mockito.when(transferDao.findById(1)).thenReturn(transfer);
		Mockito.when(transferDao.findById(1)).thenReturn(transfer);
		Mockito.when(transferDao.existsById(transfer.get().getId())).thenReturn(false);
		assertFalse(transferDao.existsById(transfer.get().getId()));
	}

	@Test
	@DisplayName("ici je teste si la methode getTransfer marche ou pas ")
	public void getTransferTest() {
		TransferService transferService = new TransferService();
		transferService.setTransferDao(transferDao);
		Optional<Transfer> transfer = Optional.of(new Transfer(1, 200));
		when(transferDao.findById(1)).thenReturn(transfer);
		Transfer tf = transferService.getTransferById(1);
		assertEquals(200, tf.getMoneyTransfer());
	}

	@Test
	@DisplayName("ici je teste si la methode updateTransfer marche ou pas ")
	public void updateTransfersTest() {
		TransferService transferSerivce = new TransferService();
		transferSerivce.setTransferDao(transferDao);
		Optional<Transfer> transfer = Optional.of(new Transfer(1, 200));
		Mockito.when(transferDao.findById(1)).thenReturn(transfer);
		transfer.get().setMoneyTransfer(100);
		Mockito.when(transferDao.save(transfer.get())).thenReturn(transfer.get());
		assertThat(transferSerivce.updateTransfer(transfer.get())).isEqualTo(transfer.get());
		assertThat(transfer.get().getId()).isEqualTo(1);

		// System.out.println(transfer.get().getMoneyTransfer());
	}

	@Test
	@DisplayName("ici je teste si la methode getAllTransfers marche ou pas ")
	public void getAllTransfersTest() {
		TransferService transferSerivce = new TransferService();
		transferSerivce.setTransferDao(transferDao);
		Transfer transfer = new Transfer(1, 200);
		Transfer transfer2 = new Transfer(2, 400);
		ArrayList<Transfer> listTransfers = new ArrayList<>();
		listTransfers.add(transfer);
		listTransfers.add(transfer2);
		Mockito.when(transferDao.findAll()).thenReturn(listTransfers);
		assertThat(transferSerivce.getAllTransfers().size()).isEqualTo(listTransfers.size());
	}
	
	
	@Test
	@DisplayName("ici je teste si la methode saveAllTransfers marche ou pas ")
	public void saveAllTransfersTest() {
		TransferService transferSerivce = new TransferService();
		transferSerivce.setTransferDao(transferDao);
		Transfer transfer = new Transfer(1, 200);
		Transfer transfer2 = new Transfer(2, 400);
		ArrayList<Transfer> listTransfers = new ArrayList<>();
		listTransfers.add(transfer);
		listTransfers.add(transfer2);		
		Mockito.when(transferDao.saveAll(listTransfers)).thenReturn(listTransfers);
		assertThat(transferSerivce.saveAllTransfers(listTransfers).size()).isEqualTo(listTransfers.size());
	}

}
