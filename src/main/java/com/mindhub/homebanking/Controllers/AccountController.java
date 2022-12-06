package com.mindhub.homebanking.Controllers;

import com.mindhub.homebanking.DTO.AccountDTO;
import com.mindhub.homebanking.DTO.ClientDTO;
import com.mindhub.homebanking.Services.AccountServices;
import com.mindhub.homebanking.Services.ClientServices;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.AccountType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class AccountController {

    @Autowired
    private AccountServices accountServices;

    @Autowired
    private ClientServices clientServices;


    @RequestMapping("/api/accounts")
    public List<AccountDTO> getAccounts() {
        return accountServices.getAccountDTO();
    }

    @RequestMapping("/api/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id) {
        return accountServices.getAccountbyId(id);

    }


    @PostMapping("/api/clients/current/accounts")
    public ResponseEntity<Object> createNewCurrentAccount(Authentication authentication , @RequestParam AccountType accountType) { //token que aloja datos de cliente autenticado( token)

        String randomNumber = "VIN-" + getRandomNumber( 10000000,99999999);

        Client newCurrentClient = clientServices.findByEmail(authentication.getName());

        if (newCurrentClient.getAccounts().size() >= 3) {
            return new ResponseEntity<>("you can not create more accounts", HttpStatus.FORBIDDEN);
        } else {

            accountServices.saveAccount(new Account(randomNumber, LocalDateTime.now(), 0.0, newCurrentClient, accountType ));

            return new ResponseEntity<>("Account created", HttpStatus.CREATED);

        }

    }

    public int getRandomNumber (int min, int max) {
        return (int) (Math.random() * (max - min) + min); // genera numero aleatorio entre mi max y min
    }

    //casting---> permite convertir dato mayor a menor

}
