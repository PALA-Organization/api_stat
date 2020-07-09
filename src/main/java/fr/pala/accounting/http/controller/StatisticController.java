package fr.pala.accounting.http.controller;

import java.util.Date;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.pala.accounting.http.exception.AuthenticationException;
import fr.pala.accounting.http.exception.InternalErrorException;
import fr.pala.accounting.login.entity.Login;
import fr.pala.accounting.statistic.entity.StatisticEntity;
import fr.pala.accounting.statistic.service.StatisticService;
import fr.pala.accounting.transaction.entity.TransactionEntity;
import fr.pala.accounting.transaction.exception.AuthenticationFailedException;
import fr.pala.accounting.transaction.request.TransactionRequest;

@RestController
public class StatisticController implements ErrorController {
    private static final String PATH = "/error";
    private final StatisticService statisticService;
    private final RestTemplateBuilder requestBuilder;
    private String accountingBaseUrl;

    public StatisticController() {
        this.statisticService = new StatisticService();
        this.requestBuilder = new RestTemplateBuilder();
        this.accountingBaseUrl = System.getenv("API_ACCOUNTING_BASE_URL");
    }

    @PostMapping(path = "statistic", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBusinessVolume(@RequestParam String accountId, @RequestParam Date startDate, @RequestParam Date endDate, @RequestBody Login login) throws RuntimeException {
        TransactionRequest request = new TransactionRequest(requestBuilder, login);
        List<TransactionEntity> result;
        try {
            result = request.getRequestMulti(this.accountingBaseUrl + "/account/" + accountId + "/transaction/");

        }catch(AuthenticationFailedException authError) {
            throw new AuthenticationException("Connexion failed");
        
        }catch(Exception e) {
            throw new InternalErrorException(e.getMessage());

        }

        return new ResponseEntity<>(this.statisticService.getStatisticData(result, startDate, endDate), HttpStatus.OK);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
    
}