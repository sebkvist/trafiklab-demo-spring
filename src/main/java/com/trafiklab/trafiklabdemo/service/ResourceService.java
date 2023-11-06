package com.trafiklab.trafiklabdemo.service;

import com.trafiklab.trafiklabdemo.exception.TrafikLabConfigException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class ResourceService {

    private static final Logger log = LoggerFactory.getLogger(ResourceService.class);

    @Value("${trafiklab.baseurl}")
    private String url;
    @Value("${trafiklab.param.model}")
    private String model;
    private String key;

    @Value("${trafiklab.param.key}")
    public void setKey(String key) {
        this.key = key;
    }
    private String mockUrl = "http://localhost:8080/api/demo/mockdata";

    public  String getRequestUrl() {
        log.info("trafiklab url: {}", url);
        log.info("trafiklab model: {}", model);
        log.info("trafiklab key: {}", key);

        if( key == null || key.isBlank()) {
            throw new TrafikLabConfigException("Request url missing key configuration! You need to set the key property in application.properties");
        }

        String requestUrl = url + "?model=" + model + "&key=" + key;
        log.info("return request url: {}", requestUrl);

        return requestUrl;
    }

    public String getMockInfo() throws IOException {
        String fileName = "file1.json";
        log.info("reading mock data from file:{}", fileName);

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        String jsontext = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return jsontext;
    }

    public String getMockUrl() {
        return mockUrl;
    }
}
