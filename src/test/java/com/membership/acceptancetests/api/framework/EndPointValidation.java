package com.membership.acceptancetests.api.framework;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public interface EndPointValidation {

    int STATUS_OK = 200;
    int STATUS_NOT_FOUND = 404;

    void setHostName(String hostName);

    void setHostPort(String hostPort);

    ResponseBody performGetRequest(String requestUrl);

    ResponseBody performPutRequest(Object resource, String requestUrlWithParameters);

    ResponseBody performDeleteRequest(String requestUrlWithParameters);

    ResponseBody performPostRequest(Object requestObject, String requestUrlWithParameters);


}
