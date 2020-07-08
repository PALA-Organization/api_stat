package fr.pala.accounting.http.request;

import java.io.Serializable;
import java.util.List;

public interface HttpRequestGETSingleAnswer {
    public Object getRequest(String path);
    public Serializable getRequestWithParams(String path, List<String> parameters);
}