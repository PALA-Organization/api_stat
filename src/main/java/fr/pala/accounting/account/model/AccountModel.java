package fr.pala.accounting.account.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountModel {

    private String account_id;
    private Double amount;
    private List<String> transactions_ids;

    public AccountModel(String account_id, Double amount, List<String> transactions_ids){
        this.account_id = account_id;
        this.amount = amount;
        this.transactions_ids = transactions_ids;
    }
}
