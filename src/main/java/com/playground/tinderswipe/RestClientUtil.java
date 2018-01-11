package com.playground.tinderswipe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playground.tinderswipe.model.RequestInfo;
import com.squareup.okhttp.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

public final class RestClientUtil {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final Logger logger = LogManager.getLogger();
    private static final OkHttpClient httpClient = new OkHttpClient();

    public static Response executeRequestFromFile(Path path, ObjectMapper objectMapper) {
        logger.debug("execute request in {}", path.getFileName());

        try {
            RequestInfo requestInfo = objectMapper.readValue(path.toFile(), RequestInfo.class);
            logger.debug("request info:{}", requestInfo);

            Request.Builder requestBuilder = new Request.Builder().url(requestInfo.getUrl());
            if (requestInfo.getMethod().equals("POST")) {
                requestBuilder.post(RequestBody.create(JSON, requestInfo.getBody()));
            }
            else if (requestInfo.getMethod().equals("PUT")) {
                requestBuilder.put(RequestBody.create(JSON, requestInfo.getBody()));
            }
            else if (requestInfo.getMethod().equals("GET")) {
                requestBuilder.get();
            }
            else if (requestInfo.getMethod().equals("DELETE")) {
                requestBuilder.delete();
            }
            else {
                throw new IllegalArgumentException("invalid request method " + requestInfo.getMethod());
            }

            requestInfo.getHeaders().entrySet().stream()
                    .forEach(e -> requestBuilder.addHeader(e.getKey(), e.getValue()));

            return httpClient.newCall(requestBuilder.build()).execute();

        } catch (IOException e) {
            logger.warn("fail to execute request in {}", path.getFileName(), e);
            throw new RuntimeException("fail to execute request");
        }

    }
}
