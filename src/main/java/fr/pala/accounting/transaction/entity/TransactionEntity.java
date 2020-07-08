package fr.pala.accounting.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TransactionEntity implements Serializable {

    private String id;
    private String type;
    private String shop_name;
    private String shop_address;
    private String date;
    private Double amount;
    private String description;

    public TransactionEntity(String id, String type, String shop_name, String shop_address, String date, Double amount, String description){
        this.id = id;
        this.type = type;
        this.shop_name = shop_name;
        this.shop_address = shop_address;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }
}
