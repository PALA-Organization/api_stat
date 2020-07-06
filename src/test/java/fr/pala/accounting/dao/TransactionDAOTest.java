package fr.pala.accounting.dao;

import fr.pala.accounting.account.dao.AccountDAO;
import fr.pala.accounting.account.model.AccountModel;
import fr.pala.accounting.transaction.dao.TransactionDAO;
import fr.pala.accounting.transaction.model.TransactionModel;
import fr.pala.accounting.user.dao.UserDAO;
import fr.pala.accounting.user.model.UserModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = { TransactionDAO.class, AccountDAO.class, UserDAO.class })
// Note : This annotation loads the classes and creates a "fake" context.
// We COULD use the original ServerApplication context but there is a mongo error as we disabled mongo
@RunWith(SpringRunner.class)
public class TransactionDAOTest {

    @MockBean
    private MongoTemplate mongoTemplate;

    @Autowired
    private TransactionDAO transactionDAO;

    @Test
    public void getAllTransactionsOfNoAccountTest() {
        //Parameters of getAllTransactionsOfAccount
        String user_id = "32352453234";
        String account_id = "12";

        //Mock the user get
        ArrayList<AccountModel> accounts = new ArrayList<>();
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(user_id));
        Mockito.when(mongoTemplate.findOne(query, UserModel.class))
                .then(ignoredInvocation -> new UserModel("32352453234", "Test", "test@test.fr", new Date(), new Date(), accounts));

        assertThat(transactionDAO.getAllTransactionsOfAccount(user_id, account_id).get()).hasSize(0);
    }

    @Test
    public void getAllTransactionsOfAccountTest() {
        //Parameters of getAllTransactionsOfAccount
        String user_id = "32352453234";
        String account_id = "12";

        //Mock the user get
        ArrayList<AccountModel> accounts = new ArrayList<>();
        ArrayList<String> transactionsIds = new ArrayList<String>();
        transactionsIds.add("235");
        transactionsIds.add("444");
        AccountModel account = new AccountModel("12", 234.55, transactionsIds);
        accounts.add(account);

        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(user_id));
        Mockito.when(mongoTemplate.findOne(query, UserModel.class))
                .then(ignoredInvocation -> new UserModel("32352453234", "Test", "test@test.fr", new Date(), new Date(), accounts));

        assertThat(transactionDAO.getAllTransactionsOfAccount(user_id, account_id).get()).hasSize(2);
    }

    @Test
    public void getTransactionTest(){
        //parameters of getTransaction
        String transactionId = "223435345345";

        TransactionModel transaction = new TransactionModel("223435345345", "Test", "Auchan",
                "Test", new Date(), 33.70, "Test");

        Query query = new Query();
        query.addCriteria(Criteria.where("transaction_id").is(transactionId));
        Mockito.when(mongoTemplate.findOne(query, TransactionModel.class))
                .then(ignoredInvocation -> transaction);

        assertThat(transactionDAO.getTransaction(transactionId).get()).isEqualTo(transaction);
    }

}
