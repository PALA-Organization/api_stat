package fr.pala.accounting.businessVolume.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessVolume {
    private String accountId;
    private Double accountValue;

    public BusinessVolume() {}

    public BusinessVolume(String accountId, Double accountValue) {
        this.accountId = accountId;
        this.accountValue = accountValue;
    }
}