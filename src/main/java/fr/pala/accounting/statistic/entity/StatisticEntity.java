package fr.pala.accounting.statistic.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticEntity {
    private Integer transactionNumber;
    private Integer numberOfDeposit;
    private Integer numberOfWithdraw;
    private Double depositQuantity;
    private Double withdrawQuantity;

    public StatisticEntity(Integer transactionNumber, Integer numberOfDeposit, Integer numberOfWithdraw, Double depositQuantity, Double withdrawQuantity) {
        this.transactionNumber = transactionNumber;
        this.numberOfDeposit = numberOfDeposit;
        this.numberOfWithdraw = numberOfWithdraw;
        this.depositQuantity = depositQuantity;
        this.withdrawQuantity = withdrawQuantity;
    }
}