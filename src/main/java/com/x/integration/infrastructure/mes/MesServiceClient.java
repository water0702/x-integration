package com.x.integration.infrastructure.mes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.x.integration.infrastructure.config.OAuth2AuthorizeClientConfig;
import io.jmix.core.security.CurrentAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizationContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author PanLei
 * @version 1.0.0
 * @createTime 2023-07-05
 */
@Component("integ_MesServiceClient")
public class MesServiceClient {
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private OAuth2AuthorizedClientManager authorizedClientManager;

    private static final Logger log = LoggerFactory.getLogger(MesServiceClient.class);


    /**
     * 发送Inbound数据到MES
     *
     */
    public String inboundMessage() {
        String mesProtocol = "http";
        String mesHost = "localhost";
        String mesPort = "8881";
        String mesPath = "/mes";
        String mesClientId = "client";
        String mesClientSecret = "secret";

        String mesUsername = "admin";
        String mesPassword = "admin";
        String inboundRequestPath = "/inbound";

        String uriStr = mesProtocol + "://" + mesHost + ":" + mesPort + mesPath + inboundRequestPath;

        Authentication principal =  currentAuthentication.getAuthentication();
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(OAuth2AuthorizeClientConfig.PRINT_REGISTRATION_ID)
                .principal(principal)
                .attribute(OAuth2AuthorizationContext.USERNAME_ATTRIBUTE_NAME, mesUsername)
                .attribute(OAuth2AuthorizationContext.PASSWORD_ATTRIBUTE_NAME, mesPassword)
                .build();
        //获取token
        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);
        if(authorizedClient == null)
        {
            throw new RuntimeException("authorizedClient is null");
        }

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        //打印请求数据
        MesRequest mesRequest = new MesRequest();

        JSONObject printRequestJson = new JSONObject();
        printRequestJson.put("printRequest", JSON.toJSONString(mesRequest));

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Authorization", "Bearer " + accessToken.getTokenValue())
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(uriStr))
                .POST(HttpRequest.BodyPublishers.ofString(printRequestJson.toString()))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpStatus.OK.value()) {
                String responseBody = response.body();
                JSONObject responseBodyJson = JSONObject.parseObject(responseBody);
                if (!responseBodyJson.getBoolean("success")) {
                    log.error(responseBody);
                    String errorMessage = responseBodyJson.getString("errorMessage");
                    throw new RuntimeException(errorMessage);
                }
                return responseBody;
            } else {
                String responseBody = response.body();
                throw new RuntimeException("fail, responseBody=" + responseBody);
            }
        } catch (IOException | InterruptedException e) {
            log.error("fail, ", e);
            throw new RuntimeException(e);
        }
    }

}
