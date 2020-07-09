package fr.pala.accounting.businessVolume.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pala.accounting.http.exception.InternalErrorException;
import fr.pala.accounting.http.service.StringToDateService;
import fr.pala.accounting.transaction.entity.TransactionEntity;


@Service
public class BusinessVolumeService {

    public Double getBusinessVolume(List<TransactionEntity> transactions, Date startDate, Date endDate) throws RuntimeException {
        Double businessVolume = Double.valueOf(0);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        StringToDateService converterStringDate = new StringToDateService(formater);

        for (TransactionEntity transactionEntity : transactions) {
            Date convertedDate;
            try {
                convertedDate = converterStringDate.mongoDateconvert(transactionEntity.getDate());

            }catch(RuntimeException exception) {
                throw new InternalErrorException(exception.getMessage());
            }


            if(convertedDate.compareTo(startDate) >= 0 && convertedDate.compareTo(endDate) <= 0) {
                businessVolume += transactionEntity.getAmount();
            }
        }

        return businessVolume;
    }

}