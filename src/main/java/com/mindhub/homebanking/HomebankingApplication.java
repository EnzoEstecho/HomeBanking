package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// corre programa usando spring
// voilerplay : Estructura que no cambia como en el html o vue

@SpringBootApplication
public class HomebankingApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);

	}
    @Bean // mete init data en un contexto general y esta a mi disposicion
	      // lo puedo usar donde quiera


	public CommandLineRunner initData(ClientRepository clientRepository , AccountRepository accountRepository , TransactionRepository transactionRepository , LoanRepository loanRepository , ClientLoanRepository clientLoanRepository, CardRepository cardRepository){   // retorna dato que se llama init data y dentro ejecuta acciones
                                                                            //le pasas el repository y ves lo que heredo
		return args -> {
		//	Client client1= new Client("Melba","Morel","MelbaMorel@gmail.com", passwordEncoder.encode("melba"));   //aca paso parametros
        //    Client client2= new Client("Fabricio","Cambria","hashd@gmail.com", passwordEncoder.encode("fabricio"));
		//	Client client3= new Client("admin", "adminsito", "ADMIN@MINDHUB", passwordEncoder.encode("admin"));

		//	clientRepository.save(client1);
		//	clientRepository.save(client2);
		//	clientRepository.save(client3);

		//	Account account1= new Account("VIN001", LocalDateTime.now(), 5000.0 , client1 );
		//	Account account2= new Account("VIN002", LocalDateTime.now().plusDays(1),7500.0, client1);
			 //guardo en base de datos cliente 1
		//	accountRepository.save(account1);
		//	accountRepository.save(account2);


		//	Transaction transaction1 = new Transaction(account1, TransactionType.DEBIT, 100, "Services", LocalDateTime.now());
		//	Transaction transaction2 = new Transaction(account2, TransactionType.CREDIT, -200, "Salary", LocalDateTime.now());
		//	Transaction transaction4 = new Transaction(account1, TransactionType.CREDIT, -800, "Salary", LocalDateTime.now());

		//	transactionRepository.save(transaction1);
		//	transactionRepository.save(transaction2);
		//	transactionRepository.save(transaction4);

		//	Loan loan1 = new Loan("Mortgage", 5000000.00, List.of(6, 12, 24, 36, 48, 60));
		//	Loan loan2 = new Loan("Personal", 1000000.00, List.of(6, 12, 24));
		//	Loan loan3 = new Loan("Vehicle", 300000.00, List.of(6, 12, 24, 36));

		//	loanRepository.save(loan1);
		//	loanRepository.save(loan2);
		//	loanRepository.save(loan3);

		//	ClientLoan clientLoan1 = new ClientLoan(400000.00, 60, client1, loan1);
		//	ClientLoan clientLoan2 = new ClientLoan(50000.00, 12, client1, loan2);
		//	ClientLoan clientLoan3 = new ClientLoan(100000.00, 24, client2, loan2);
		//	ClientLoan clientLoan4 = new ClientLoan(200000.00, 36, client2, loan3);

		//	clientLoanRepository.save(clientLoan1);
		//	clientLoanRepository.save(clientLoan2);
		//	clientLoanRepository.save(clientLoan3);
		//	clientLoanRepository.save(clientLoan4);

		//	Card card1 = new Card( client1 , client1.getFirstName() + " " + client1.getLastName() , "6666-6666-6666-6666", 666, LocalDate.now(), LocalDate.now().plusYears(5), CardType.DEBIT, CardColor.GOLD);
		//	Card card2 = new Card( client1 ,client1.getFirstName() + " " + client1.getLastName(), "1111-4444-5555-3333", 578, LocalDate.now(), LocalDate.now().plusYears(5), CardType.DEBIT, CardColor.TITANIUM );
		//	Card card3 = new Card(client2, client2.getFirstName() + " " + client2.getLastName() , "5678-1234-4949-6969", 422, LocalDate.now(), LocalDate.now().plusYears(5), CardType.DEBIT, CardColor.SILVER);

		//	cardRepository.save(card1);
		//	cardRepository.save(card2);
		//	cardRepository.save(card3);
		};
	}






}
