package fr.pala.accounting.user.dao;

import fr.pala.accounting.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public UserDAO(){
    }

    public UserModel addUser(UserModel user) {
        return mongoTemplate.save(user);
    }

    public Optional<List<UserModel>> getAllUsers() {
        return Optional.of(mongoTemplate.findAll(UserModel.class));
    }

    public Optional<UserModel> getUserById(String user_id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(user_id));
        return Optional.of(mongoTemplate.findOne(query, UserModel.class));
    }
}
