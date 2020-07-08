package fr.pala.accounting.http.request;

import java.io.Serializable;
import java.util.List;

public interface HttpRequestGETMultiAnswer {
    public Serializable[] getRequestMulti(String path);
    public Serializable[] getRequestMultiWithParams(String path, List<String> parameters);
}