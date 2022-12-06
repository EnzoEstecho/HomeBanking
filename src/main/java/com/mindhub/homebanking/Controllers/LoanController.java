package com.mindhub.homebanking.Controllers;

import com.mindhub.homebanking.DTO.LoanApplicationDTO;
import com.mindhub.homebanking.DTO.LoanDTO;
import com.mindhub.homebanking.Services.*;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoanController {
    @Autowired
    LoanServices loanServices;

    @Autowired
    ClientServices clientServices;

    @Autowired
    AccountServices accountServices;

    @Autowired
    TransactionServices transactionServices;

    @Autowired
    ClientLoanServices clientLoanServices;


    @RequestMapping("/api/loans")
    public List<LoanDTO> getLoanApplicationDTO() {

        return loanServices.getLoanDTO();
    }


    @Transactional //  Detecta un fallo y si hay uno borra

    @PostMapping("/api/loans")
    public ResponseEntity<Object> newLoans(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication) { //token que aloja datos de cliente autenticado( token)

        Client clientCurrent = clientServices.findByEmail(authentication.getName());
        Loan loanExist = loanServices.getLoanbyId(loanApplicationDTO.getId());
        Account accountDestiny = accountServices.findByNumber(loanApplicationDTO.getNumberDestinAccount());

        if (clientCurrent != null) {

            //verifico que los datos no esten vacios

            if (loanApplicationDTO == null) {
                return new ResponseEntity<>("DATA NOT FOUND", HttpStatus.FORBIDDEN);
            }

            if (loanApplicationDTO.getAmount() <= 0) {
                return new ResponseEntity<>("the amount is empty", HttpStatus.FORBIDDEN);
            }


            if (loanApplicationDTO.getNumberDestinAccount().isEmpty()) {
                return new ResponseEntity<>("the destination account is empty", HttpStatus.FORBIDDEN);
            }

            if (loanApplicationDTO.getPayments() == null) {
                return new ResponseEntity<>("the payments are empty" , HttpStatus.FORBIDDEN);
            }

            if (loanApplicationDTO.getId() == null) {
                return new ResponseEntity<>("the loan is empty", HttpStatus.FORBIDDEN);
            }


            if (accountDestiny == null) {
                return new ResponseEntity<>(  "There is no account to charge the loan", HttpStatus.FORBIDDEN);

            }

            if (!clientCurrent.getAccounts().contains(accountDestiny)) {
                return new ResponseEntity<>("accounts cannot be authenticated", HttpStatus.FORBIDDEN);
            }

            if (loanExist == null) {
                return new ResponseEntity<>("the loan could not be verified", HttpStatus.FORBIDDEN);
            }

            if (loanApplicationDTO.getAmount() > loanExist.getMaxAmount()) {
                return new ResponseEntity<>("exceeds the amount available", HttpStatus.FORBIDDEN);
            }


            if (!loanExist.getPayments().contains(loanApplicationDTO.getPayments())) {
                return new ResponseEntity<>("the payments are not among those available ", HttpStatus.FORBIDDEN);
            }

            if (clientCurrent.getClientLoans().stream().filter(clientLoan -> clientLoan.getLoan().getName().equals(loanExist.getName())).toArray().length >= 1) {
                return new ResponseEntity<>("you already have a loan ", HttpStatus.FORBIDDEN);
            }




            if (loanApplicationDTO.getId() == 7) {
                ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount() * 1.2, loanApplicationDTO.getPayments(), clientCurrent, loanExist);
                clientLoanServices.saveClientLoan(clientLoan);
            }

            if (loanApplicationDTO.getId()==8){
                ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount() * 1.10, loanApplicationDTO.getPayments(), clientCurrent , loanExist);
                clientLoanServices.saveClientLoan(clientLoan);
            }

            if(loanApplicationDTO.getId()==9){
                ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount() * 1.5, loanApplicationDTO.getPayments(), clientCurrent , loanExist);
                clientLoanServices.saveClientLoan(clientLoan);
            }



            Transaction transactionCreated = new Transaction(accountDestiny, TransactionType.CREDIT, loanApplicationDTO.getAmount(), " from " + accountDestiny.getNumber(), LocalDateTime.now());
            transactionServices.saveTransaction(transactionCreated);

            accountDestiny.setBalance(accountDestiny.getBalance() + loanApplicationDTO.getAmount());
            accountServices.saveAccount(accountDestiny);

            return new ResponseEntity<>(HttpStatus.CREATED);



        }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);





    }


    @PostMapping("/api/admin/loans")
    public ResponseEntity<String> addAdminLoan (@RequestParam String name,@RequestParam Double amount, @RequestParam List<Integer> payments, Authentication authentication){

        Client adminAuthentication = clientServices.findByEmail(authentication.getName());

        if(adminAuthentication == null){
            return new ResponseEntity<>("missing admin authentication", HttpStatus.FORBIDDEN);
        }

        if(name.isEmpty()){
            return new ResponseEntity<>("missing name of loan", HttpStatus.FORBIDDEN);
        }

        if(amount <= 0){
            return new ResponseEntity<>("missing max amount of loan", HttpStatus.FORBIDDEN);
        }

        if(loanServices.getLoans().stream().map(x -> x.getName()).collect(Collectors.toSet()).contains(name)){
            return new ResponseEntity<>("same name of previous loan", HttpStatus.FORBIDDEN);
        }

        //loanServices.saveLoan(new Loan(name, amount, payments));
        Loan loan = new Loan(name, amount, payments);
        //loanServices.saveLoan(loan);
        loanServices.saveLoan(loan);

        return new ResponseEntity<>("Loan Created",HttpStatus.CREATED);
    }
}
