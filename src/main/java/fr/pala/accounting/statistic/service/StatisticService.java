package fr.pala.accounting.statistic.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.pala.accounting.http.exception.InternalErrorException;
import fr.pala.accounting.http.service.StringToDateService;
import fr.pala.accounting.statistic.entity.StatisticEntity;
import fr.pala.accounting.transaction.entity.TransactionEntity;

public class StatisticService {
    public StatisticEntity getStatisticData(List<TransactionEntity> transactions, Date startDate, Date endDate) {
        StatisticEntity stat = new StatisticEntity(0, 0, 0, 0.00, 0.00);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        StringToDateService converterStringDate = new StringToDateService(formater);

        for (TransactionEntity transactionEntity : transactions) {
            Date convertedDate;
            try {
                convertedDate = converterStringDate.mongoDateconvert(transactionEntity.getDate());

            }catch(RuntimeException exception) {
                throw new InternalErrorException(exception.getMessage());
            }

            if(convertedDate.compareTo(startDate) < 0 || convertedDate.compareTo(endDate) > 0) {
                continue;
            }
            
            Double currentAmount = transactionEntity.getAmount();
            stat.setTransactionNumber(stat.getTransactionNumber() + 1);

            if(currentAmount >= 0) {
                stat.setNumberOfDeposit(stat.getNumberOfDeposit() + 1);
                stat.setDepositQuantity(stat.getDepositQuantity() + currentAmount);
            
            }else {
                stat.setNumberOfDeposit(stat.getNumberOfDeposit() + 1);
                stat.setWithdrawQuantity(stat.getWithdrawQuantity() - currentAmount);
            }
        }

        return stat;
    }

    
}