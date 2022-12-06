//el DTO (data transfer objets) evita que me aparezcan solamente los datos del cliente, sin sus cuentas y sus detalles

package com.mindhub.homebanking.DTO;


import com.mindhub.homebanking.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;



public class ClientDTO {
    private String firstName;

    private String lastName;
    private String email;

    private long id;

    private Set<AccountDTO> accounts = new HashSet<>(); // lista que no se repite

    private Set<ClientLoanDTO> loans = new HashSet<>();

    private Set<CardDTO> cards = new HashSet<>();

    public ClientDTO() {}

    public ClientDTO(Client client) { // cliente que clintcontroller me pida ( del cliente del momento me trae tod)
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.accounts = client.getAccounts().stream().map(account -> new AccountDTO(account)).collect(Collectors.toSet());
        this.loans = client.getClientLoans().stream().map(clientLoan -> new ClientLoanDTO(clientLoan)).collect(Collectors.toSet());
        this.cards = client.getCards().stream().map(card -> new CardDTO(card) ).collect(Collectors.toSet());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {return lastName;}

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public Set<ClientLoanDTO> getLoans() {
        return loans;
    }

    public Set<CardDTO> getCards() {
        return cards;
    }

}