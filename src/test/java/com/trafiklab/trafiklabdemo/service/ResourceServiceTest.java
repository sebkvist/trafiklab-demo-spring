package com.trafiklab.trafiklabdemo.service;

import com.trafiklab.trafiklabdemo.exception.TrafikLabConfigException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceServiceTest {

    @Autowired
    private ResourceService resourceService;

    //
    @Test
    void getRequestUrl() {
        resourceService.setKey("abcde");
        String requestUrl = resourceService.getRequestUrl();
        Assertions.assertThat(requestUrl).isNotEmpty();
        Assertions.assertThat(requestUrl).contains("LineData.json");
    }

    @Test
    void getRequestUrlThrows() {
        resourceService.setKey(null);

        TrafikLabConfigException thrown = assertThrows(TrafikLabConfigException.class, () -> {
            //Code under test
            String requestUrl = resourceService.getRequestUrl();
        });

    }

    @Test
    void getMockInfo() throws IOException  {
        Assertions.assertThat(resourceService.getMockInfo()).isNotEmpty();
    }

}