package fr.pala.accounting.http.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateService {
    private SimpleDateFormat formater;
    
    public StringToDateService(SimpleDateFormat formater) {
        this.formater = formater;
    }

    public Date mongoDateconvert(String dateString) {
        Date result = new Date();
        String[] splitedResult = dateString.split("\\.");
        String toFormate = splitedResult[0];
        try {
            result = this.formater.parse(toFormate);

        }catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}