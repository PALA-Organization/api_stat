package fr.pala.accounting.businessVolume.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pala.accounting.http.service.StringToDateService;
import fr.pala.accounting.transaction.entity.TransactionEntity;


@Service
public class BusinessVolumeService {

    // private final Business

    public Double getBusinessVolume(List<TransactionEntity> transactions, Date startDate, Date endDate) throws RuntimeException {
        Double businessVolume = Double.valueOf(0);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        System.out.println("PUTE");
        StringToDateService converterStringDate = new StringToDateService(formater);
        System.out.println("PUTE");

        for (TransactionEntity transactionEntity : transactions) {
            System.out.println("PUTE");
            Date convertedDate = converterStringDate.mongoDateconvert(transactionEntity.getDate());
            System.out.println("PUTE");

            if(convertedDate.compareTo(startDate) >= 0 && convertedDate.compareTo(endDate) <= 0) {
                businessVolume += transactionEntity.getAmount();
            }
        }

        return businessVolume;
    }

    // public BusinessVolume getBusinessVolume(String userId, String accountId, Date limiteDateEvaluation) {
    //     // Optional<List<TransactionModel>> accountTransactions = transactionDAO.getAllTransactionsOfAccount(userId, accountId);

    //     // if(accountTransactions.isEmpty()) {
    //     //     throw new NoAccountException("This account doesn't exist");
    //     // }

    //     // Conditional MAP for date
    //     // Double result = accountTransactions.get().stream().map(x -> x.getAmount()).reduce((x, y) -> x + y).get();
    //     BusinessVolume businessVolume = new BusinessVolume(accountId, 0.00);

    //     return businessVolume;
    // }

    // public List<BusinessVolume> getBusinessVolumeForEachAccount(String userId) {
    //     Optional<List<AccountModel>> accounts = accountDAO.getAllAccountsOfUsers(userId);
    //     ArrayList<BusinessVolume> businessVolumes = new ArrayList<>();

    //     if(accounts.isEmpty()) {
    //         throw new NoAccountException("No account for this user");
    //     }

    //     for (AccountModel account : accounts.get()) {
            
    //         businessVolumes.add(new BusinessVolume(account.getAccount_id(), account.getAmount()));
    //     }

    //     return businessVolumes.toList();
    // }
}