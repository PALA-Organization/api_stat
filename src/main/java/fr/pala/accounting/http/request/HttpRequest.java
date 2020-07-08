package fr.pala.accounting.http.request;

import java.io.Serializable;
import java.util.List;

public interface HttpRequest {
    public String getLoginToken(String email, String password);
}