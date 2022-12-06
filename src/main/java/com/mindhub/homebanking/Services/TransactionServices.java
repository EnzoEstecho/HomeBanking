package com.mindhub.homebanking.Services;


import com.mindhub.homebanking.DTO.ClientDTO;
import com.mindhub.homebanking.DTO.TransactionDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface TransactionServices {

    public List<TransactionDTO> getTransactionDTO();

    public TransactionDTO getTransactionbyId(long id);

    public void saveTransaction(Transaction transaction);

    public Set<Transaction> getTransactionByDate (LocalDateTime dateFrom, LocalDateTime dateTo);


}
