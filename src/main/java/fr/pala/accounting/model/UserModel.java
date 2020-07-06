package fr.pala.accounting.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import fr.pala.accounting.serializer.UserJsonSerializer;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@Document(collection = "User")
@Getter
@Setter
@JsonSerialize(using = UserJsonSerializer.class)
public class UserModel {

    @Id
    private String user_id;
    private String name;
    private String email;
    private Date created;
    private Date last_connection;
    private List<AccountModel> accounts;

    public UserModel(String user_id, String name, String email, Date created, Date last_connection, List<AccountModel> accounts){
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.created = created;
        this.last_connection = last_connection;
        this.accounts = accounts;
    }
}
