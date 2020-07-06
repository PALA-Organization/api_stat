package fr.pala.accounting.account.dao;

import fr.pala.accounting.user.dao.UserDAO;
import fr.pala.accounting.account.model.AccountModel;
import fr.pala.accounting.user.model.UserModel;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserDAO userDAO;

    public Optional<List<AccountModel>> getAllAccountsOfUsers(String user_id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(user_id));

        UserModel user = mongoTemplate.findOne(query, UserModel.class);

        if(user != null){
            return Optional.of(user.getAccounts());
        }

        return Optional.empty();
    }

    public Optional<AccountModel> getAccountOfUser(String user_id, String account_id) {
        Optional<List<AccountModel>> accounts = getAllAccountsOfUsers(user_id);      
        AccountModel accountResult = null;

        if(accounts.isEmpty()) {
            return Optional.empty();
        }

        for (AccountModel account : accounts.get()) {
            if (account.getAccount_id().equals(account_id)) {
                accountResult = account;
                break;
            }
        }
        return Optional.of(accountResult);
    }

    public Optional<Double> getAmountOfAccount(String user_id, String account_id) {
        Optional<AccountModel> account = getAccountOfUser(user_id, account_id);

        if(account.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(account.get().getAmount());
    }

}
