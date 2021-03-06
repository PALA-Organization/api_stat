package fr.pala.accounting.transaction.request;

import java.io.Serializable;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import fr.pala.accounting.http.request.HttpRequest;
import fr.pala.accounting.login.entity.Login;
import fr.pala.accounting.transaction.entity.TransactionEntity;
import fr.pala.accounting.transaction.exception.AuthenticationFailedException;
import fr.pala.accounting.transaction.exception.GetTransactionException;
import lombok.Getter;
import lombok.Setter;

public class TransactionRequest {

    private final RestTemplate restTemplate;
    private final String email;
    private final String password;
    private HttpHeaders headers;
    
    private String loginPath;

    public TransactionRequest(final RestTemplateBuilder restTemplateBuilder, final Login login) {
        Map<String, String> env = System.getenv();

        this.restTemplate = restTemplateBuilder.build();
        this.headers = new HttpHeaders();

        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        this.email = login.getEmail();
        this.password = login.getPassword();

        this.loginPath = env.get("API_ACCOUNTING_BASE_URL") + "/login";
    }

    public List<TransactionEntity> getRequestMulti(String path) throws RuntimeException {
        String token = getLoginToken(this.email, this.password);

        this.headers.setBearerAuth(token);

        HttpEntity entity = new HttpEntity(this.headers);

        final ResponseEntity<String> response = this.restTemplate.exchange(
            path, 
            HttpMethod.GET, 
            entity, 
            String.class
        );

        if(response.getStatusCode() != HttpStatus.OK) {
            throw new GetTransactionException("Failed to get data from account api");
        }

        ArrayList<TransactionEntity> listTransactions = new ArrayList<>();
        TransactionEntity[] jsonObject = new Gson().fromJson(response.getBody(), TransactionEntity[].class);
        
        for (TransactionEntity transactionEntity : jsonObject) {
            TransactionEntity currentEntity = new TransactionEntity(
                transactionEntity.getId(), 
                transactionEntity.getType(), 
                transactionEntity.getShop_name(), 
                transactionEntity.getShop_address(), 
                transactionEntity.getDate(), 
                transactionEntity.getAmount(), 
                transactionEntity.getDescription()
            );
            listTransactions.add(currentEntity);
        }

        return  listTransactions;
    }

    public TransactionEntity[] getRequestMultiWithParams(String path, List<String> parameters) {
        return null;
    }

    public String getLoginToken(String email, String password) throws RuntimeException {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, this.headers);
        ResponseEntity<String> responseLogin;

        try {
            responseLogin = this.restTemplate.postForEntity(this.loginPath, entity, String.class);
        }catch(RestClientException execption) {
            throw new AuthenticationFailedException("Failed to authenticate over authentication provider");
        }

        return responseLogin.getHeaders().get("Authorization").get(0).split(" ")[1];
    }

}