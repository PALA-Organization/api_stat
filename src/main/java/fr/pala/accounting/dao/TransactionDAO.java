package fr.pala.accounting.dao;

import fr.pala.accounting.model.AccountModel;
import fr.pala.accounting.model.TransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AccountDAO accountDAO;

    public Optional<List<TransactionModel>> getAllTransactionsOfAccount(String user_id, String account_id) {
        List<TransactionModel> transactionResults = new ArrayList<TransactionModel>();
        Optional<AccountModel> accountModel = accountDAO.getAccountOfUser(user_id, account_id);

        if(accountModel.isEmpty()){
            return Optional.empty();
        }

        ArrayList<String> transactions_ids = accountModel.get().getTransactions_ids().toList();

        for (String transactions_id : transactions_ids) {
            Query query = new Query();
            query.addCriteria(Criteria.where("transaction_id").is(transactions_id));
            transactionResults.add(mongoTemplate.findOne(query, TransactionModel.class));
        }

        return Optional.of(transactionResults);
    }

    public Optional<TransactionModel> getTransaction(String transaction_id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("transaction_id").is(transaction_id));
        return Optional.of(mongoTemplate.findOne(query, TransactionModel.class));
    }
}
