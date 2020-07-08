package fr.pala.accounting.http.controller;

import fr.pala.accounting.http.exception.NotDataFoundException;
import fr.pala.accounting.login.entity.Login;
import fr.pala.accounting.transaction.entity.TransactionEntity;
import fr.pala.accounting.transaction.request.TransactionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@RestController
public class HomeController implements ErrorController {

    private static final String PATH = "/error";
    private final RestTemplateBuilder requestBuilder;

    HomeController() {
        requestBuilder = new RestTemplateBuilder();
    }

    @GetMapping("/")
    public String writeHello() {
        return "Welcome home";
    }

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionEntity> test1(@RequestHeader (name="Authorization") String token) {
        TransactionRequest request = new TransactionRequest(requestBuilder, new Login("kmoxsom0@hexun.com", "test"));
        List<TransactionEntity> transactions = request.getRequestMulti("http://127.0.0.1:8080/account/5ef325ac9792693404c0bc72/transaction/");
        return transactions;
    }

    @GetMapping(path = "/test2", produces = MediaType.APPLICATION_JSON_VALUE)
    public Date test2() {
        String result = "2020-04-05T06:48:42.000+0000";
        Date date2 = new Date();
        String[] result2 = result.split("\\.");
        String result3 = result2[0];
        try {
            date2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(result3);

        }catch(Exception e) {
            e.printStackTrace();
        }

        // throw new NotDataFoundException();
        return date2;
    }



    @RequestMapping(value = PATH)
    public String error() {
        return "Error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}
