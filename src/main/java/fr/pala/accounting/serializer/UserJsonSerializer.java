package fr.pala.accounting.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import fr.pala.accounting.model.AccountModel;
import fr.pala.accounting.model.TransactionModel;
import fr.pala.accounting.model.UserModel;

public class UserJsonSerializer extends StdSerializer<UserModel> {
    
    public UserJsonSerializer() {
        this(null);
    }
   
    public UserJsonSerializer(Class<UserModel> user) {
        super(user);
    }

    @Override
    public void serialize(UserModel value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        
        jgen.writeStartObject();
        jgen.writeStringField("user_id", value.getUser_id());
        jgen.writeStringField("name", value.getName());
        jgen.writeStringField("email", value.getEmail());
        jgen.writeStringField("created", value.getCreated().toString());
        jgen.writeStringField("last_connection", value.getLast_connection().toString());
        jgen.writeFieldName("accounts");
        jgen.writeStartArray();
        
        if(value.getAccounts() == null) {
            jgen.writeEndArray();
            jgen.writeEndObject();
            return;
        }

        for (AccountModel account : value.getAccounts()) {
            jgen.writeStartObject();
            jgen.writeStringField("account_id", account.getAccount_id());
            jgen.writeNumberField("amount", account.getAmount());
            jgen.writeFieldName("transactions_ids");
            jgen.writeStartArray();
            
            if(account.getTransactions_ids() != null) {
                for (String transaction : account.getTransactions_ids()) {
                
                    jgen.writeString(transaction);
                    
                }
            }

            jgen.writeEndArray();
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
        jgen.writeEndObject();
    }
    
}