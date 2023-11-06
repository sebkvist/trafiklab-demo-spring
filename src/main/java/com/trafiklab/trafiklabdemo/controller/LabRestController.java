package com.trafiklab.trafiklabdemo.controller;

import com.trafiklab.trafiklabdemo.controller.dto.DemoResponse;
import com.trafiklab.trafiklabdemo.controller.dto.TopLine;
import com.trafiklab.trafiklabdemo.model.BusStop;
import com.trafiklab.trafiklabdemo.model.TrafikLabResponse;
import com.trafiklab.trafiklabdemo.service.ResourceService;
import com.trafiklab.trafiklabdemo.service.TrafikLabDataCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/demo")
public class LabRestController {
    private static final Logger log = LoggerFactory.getLogger(LabRestController.class);

    private final RestTemplate restTemplate;

    private final ResourceService resourceService;

    @Autowired
    public LabRestController(RestTemplate restTemplate, ResourceService resourceService) {
        this.restTemplate = restTemplate;
        this.resourceService =resourceService;
    }

    private TrafikLabDataCollector trafikLabDataCollector = new TrafikLabDataCollector();


    @GetMapping("/greeting")
    public String greeting() {
        log.info("greeting trafikinfo");

        return "success!";
    }

    @GetMapping("/mockdata")
    public String internalMockdata() throws IOException {
        log.info("rest: mockdata called...");
        return resourceService.getMockInfo();
    }

    @GetMapping("/mockinfo")
    public DemoResponse mockinfo() throws IOException {
        log.info("getting mockinfo");
        TrafikLabResponse data = restTemplate.getForObject(resourceService.getMockUrl(), TrafikLabResponse.class);
        log.info("response data: " + data.responseData().result());

        BusStop[] result = data.responseData().result();
        List<TopLine> topTen = trafikLabDataCollector.getTopTenLinesList(result);
        Optional<TopLine> firstEntry = topTen.stream().findFirst();
        List<String> busStops = trafikLabDataCollector.getAllBusStopsForLine(result, firstEntry.get().lineName());

        DemoResponse demoResponse = new DemoResponse(topTen, busStops);
        return demoResponse;
    }

    @GetMapping("/trafikinfo")
    public DemoResponse trafikinfo() {
        log.info("serving trafikinfo...");
        String requestUrl = resourceService.getRequestUrl();
        log.info("trafiklab url: {}", requestUrl);

        TrafikLabResponse data = restTemplate.getForObject(requestUrl, TrafikLabResponse.class);
        log.info(data.toString());
        BusStop[] result = data.responseData().result();
        log.info("response data: " + data.responseData().result());

        List<TopLine> topTen = trafikLabDataCollector.getTopTenLinesList(result);
        Optional<TopLine> firstEntry = topTen.stream().findFirst();
        List<String> busStops = trafikLabDataCollector.getAllBusStopsForLine(result, firstEntry.get().lineName());

        DemoResponse demoResponse = new DemoResponse(topTen, busStops);
        return demoResponse;
    }



}
