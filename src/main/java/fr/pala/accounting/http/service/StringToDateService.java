package fr.pala.accounting.http.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.pala.accounting.http.exception.InternalErrorException;

public class StringToDateService {
    private SimpleDateFormat formater;
    
    public StringToDateService(SimpleDateFormat formater) {
        this.formater = formater;
    }

    public Date mongoDateconvert(String dateString) throws RuntimeException {
        Date result = new Date();
        String[] splitedResult = dateString.split("\\.");
        String toFormate = splitedResult[0];
        try {
            result = this.formater.parse(toFormate);

        }catch(Exception e) {
            throw new InternalErrorException(e.getMessage());
        }

        return result;
    }
}