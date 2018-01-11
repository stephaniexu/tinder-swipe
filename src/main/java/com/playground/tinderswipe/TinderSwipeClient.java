package com.playground.tinderswipe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.playground.tinderswipe.model.Account;
import com.playground.tinderswipe.model.RequestInfo;
import com.squareup.okhttp.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TinderSwipeClient {
    private static final Logger logger = LogManager.getLogger();

    private static final String CREATE_ACCOUNT_URI = "http://localhost:8080/account";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final ObjectMapper objectMapper;
    private final OkHttpClient httpClient;

    public TinderSwipeClient() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JodaModule());
        this.httpClient = new OkHttpClient();
    }

    public Account createAccount(String requestJson) {
        logger.debug("create account {}", requestJson);

        RequestBody requestBody = RequestBody.create(JSON, requestJson);
        Request request = new Request.Builder()
                .url(CREATE_ACCOUNT_URI)
                .put(requestBody)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            Account account = objectMapper.readValue(response.body().string(), Account.class);
            logger.debug("Account created: {}");
            return account;
        } catch (IOException e) {
            logger.warn("fail to execute create account request {}", requestJson, e);
        }
        return null;
    }

    public Account createAccount(Path path) {
        logger.debug("create account from file {}", path.getFileName());

        try {
            RequestInfo requestInfo = objectMapper.readValue(path.toFile(), RequestInfo.class);
            logger.debug("request info:{}", requestInfo);

            Request.Builder requestBuilder = new Request.Builder().url(requestInfo.getUrl());
            requestBuilder.put(RequestBody.create(JSON, requestInfo.getBody()));
            Response response = httpClient.newCall(requestBuilder.build()).execute();
            Account account = objectMapper.readValue(response.body().string(), Account.class);
            logger.debug("Account created: {}", account);

            String accountId = account.getId();
            account.setId(null);
            String outJson = objectMapper.writeValueAsString(account);
            String inJson = objectMapper.writeValueAsString(
                    objectMapper.readValue(requestInfo.getBody(), Account.class));
            if (!outJson.equals(inJson)) {
                logger.warn("output is not the same as input");
            }

            account.setId(accountId);
            return account;
        } catch (IOException e) {
            logger.warn("fail to execute create account request in {}", path.getFileName());
        }
        return null;
    }


    public List<Account> createAccountsFromFile() {
        try {
            List<Account> accounts = Files.walk(
                    Paths.get(getClass().getClassLoader().getResource("input").toURI()))
                    .filter(Files::isRegularFile)
                    .map(this::createAccount)
                    .collect(Collectors.toList());

        } catch (IOException | URISyntaxException e) {
            logger.warn("fail to create accounts", e);
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        TinderSwipeClient swipeClient = new TinderSwipeClient();
        /*
        String accountJson = "{\n" +
                "\"firstName\": \"Amy\",\n" +
                "\"lastName\": \"Ferris\",\n" +
                "\"photoUrl\": \"http://testcdn.com/amyferris.png\",\n" +
                "\"gender\": \"female\",\n" +
                "\"birthday\":\"1990-02-07\"\n" +
                "}" ;
        System.out.println(swipeClient.createAccount(accountJson));
        */

        swipeClient.createAccountsFromFile();

    }


}
