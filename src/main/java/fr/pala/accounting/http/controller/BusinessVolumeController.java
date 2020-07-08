package fr.pala.accounting.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.pala.accounting.businessVolume.service.BusinessVolumeService;
import fr.pala.accounting.http.exception.InternalErrorException;
import fr.pala.accounting.login.entity.Login;
import fr.pala.accounting.transaction.entity.TransactionEntity;
import fr.pala.accounting.transaction.request.TransactionRequest;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BusinessVolumeController implements ErrorController {

    private static final String PATH = "/error";
    private final BusinessVolumeService businessVolumeService;
    private final RestTemplateBuilder requestBuilder;
    private String accountingBaseUrl;

    public BusinessVolumeController() {
        this.businessVolumeService = new BusinessVolumeService();
        this.requestBuilder = new RestTemplateBuilder();
        this.accountingBaseUrl = System.getenv("API_ACCOUNTING_BASE_URL");
    }

    @PostMapping(path = "business-volume", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBusinessVolume(@RequestParam String accountId, @RequestParam Date startDate, @RequestParam Date endDate, @RequestBody Login login) throws RuntimeException {
        System.out.println("TEST");
        System.out.println(startDate + " " + endDate);
        TransactionRequest request = new TransactionRequest(requestBuilder, login);
        System.out.println("TEST");
        List<TransactionEntity> result;
        try {
            System.out.println("TEST TRY 1");
            System.out.println(this.accountingBaseUrl + "/account/" + accountId + "/transaction/");
            result = request.getRequestMulti(this.accountingBaseUrl + "/account/" + accountId + "/transaction/");
            System.out.println("TEST TRY 2");
        }catch(Exception e) {
            e.printStackTrace();
            throw new InternalErrorException(e.getMessage());
        }
        System.out.println("TEST BEFORE END");

        // if(user.isEmpty()) {
        //     return null;
        // }

        // Double result = businessVolumeService.getBusinessVolumeOfAllAccounts(userId, new Date());
        return new ResponseEntity<>(this.businessVolumeService.getBusinessVolume(result, startDate, endDate), HttpStatus.OK);
    }

    

    @Override
    public String getErrorPath() {
        return PATH;
    }
    
}