package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.ClientLoan;

import java.util.List;

public class ClientLoanDTO {

    private long id;
    private long loan_id;
    private Double amount;

    private Integer payments;
    private String name;





    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
        this.loan_id = clientLoan.getLoan().getId();
        this.name = clientLoan.getLoan().getName();


    }

    public long getId() {
        return id;
    }

    public long getLoan_id() {
        return loan_id;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public String getName() {
        return name;
    }
}
