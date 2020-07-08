package fr.pala.accounting.businessVolume.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BusinessVolumeService {

    // private final Business

    // public Double getBusinessVolumeOfAllAccounts(String userId, Date limiteDateEvaluation) throws RuntimeException {
    //     Optional<List<AccountModel>> accounts = accountDAO.getAllAccountsOfUsers(userId);
    //     ArrayList<List<TransactionModel>> transactionsOfAccounts = new ArrayList<>();
    //     Double businessVolume = Double.valueOf(0);

    //     if(accounts.isEmpty()) {
    //         throw new NoAccountException("No account for this user");
    //     }

    //     for (AccountModel account : accounts.get()) {
    //         Optional<List<TransactionModel>> transactions = transactionDAO.getAllTransactionsOfAccount(userId, account.getTransaction_id());
    //         // Date check
    //         // transactions.filter(predicate)
    //         transactionsOfAccounts.add(transactions.get());
    //     }

    //     for (List<TransactionModel> list : transactionsOfAccounts) {
    //         businessVolume += list.stream().map(x -> x.getAmount()).reduce((x, y) -> x + y).get();
    //     }
        

    //     return 0.00;
    // }

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