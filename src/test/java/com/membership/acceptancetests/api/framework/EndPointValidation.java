package com.membership.acceptancetests.api.framework;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public interface EndPointValidation {

    int STATUS_OK = 200;
    int STATUS_NOT_FOUND = 404;

    void setHostName(String hostName);

    void setHostPort(String hostPort);

    Response performGetRequest(String requestUrl, String cardId, String pin);

    Response performPutRequest(Object resource, String requestUrlWithParameters);

    Response performDeleteRequest(String requestUrlWithParameters);

    Response performPostRequest(Object requestObject, String requestUrlWithParameters);


}
