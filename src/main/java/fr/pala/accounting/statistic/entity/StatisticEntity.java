package fr.pala.accounting.statistic.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticEntity {
    private Integer numberOfDeposit;
    private Integer numberOfWithdraw;
    private Double depositQuantity;
    private Double withdrawQuantity;
}