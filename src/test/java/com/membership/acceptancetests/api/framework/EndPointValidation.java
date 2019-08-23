package com.membership.acceptancetests.api.framework;

import io.restassured.response.Response;

public interface EndPointValidation {

    int STATUS_OK = 200;
    int STATUS_NOT_FOUND = 404;

    void setHostName(String hostName);

    void setHostPort(String hostPort);

    Response performGetRequest(String requestUrl);

    Response performPutRequest(Object resource, String requestUrlWithParameters);

    Response performDeleteRequest(String requestUrlWithParameters);

    Response performPostRequestUnauthenticated(Object requestObject, String requestUrlWithParameters);

    Response performPostRequestAuthenticated(String username, String pin, String requestUrl);
}
