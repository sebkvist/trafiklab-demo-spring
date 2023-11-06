package com.trafiklab.trafiklabdemo.service;

import com.trafiklab.trafiklabdemo.controller.LabRestController;
import com.trafiklab.trafiklabdemo.controller.dto.TopLine;
import com.trafiklab.trafiklabdemo.model.BusStop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrafikLabDataCollector {

    private static final Logger log = LoggerFactory.getLogger(TrafikLabDataCollector.class);

    /**
     * Filtrerar ut och sorterar de linjer med flest hållplatser.
     *
     * @param busStops
     *
     * @return en sorterad list  med linje, och dess antal hållplatser.
     */
    public List<TopLine> getTopTenLinesList(BusStop[] busStops) {
        List<BusStop> list = Arrays.asList(busStops);
        Map<String, Long> counted = list.stream()
                .collect(Collectors.groupingBy(BusStop::lineNumber, Collectors.counting()));
        log.info("grouped lines, count:{}, content:{}", counted.size(), counted);

        List<TopLine> sorted = counted.entrySet()
                .stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .map(entry -> new TopLine(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        log.info("sorted top ten , count:{}, content:{}", sorted.size(), sorted);
        return sorted;
    }

    public List<String> getAllBusStopsForLine(BusStop[] busStops, String lineNumber) {
        List<BusStop> list = Arrays.asList(busStops);
        return list.stream()
                .filter( entry -> lineNumber.equals(entry.lineNumber()))
                .map(entry -> entry.journeyPatternPointNumber())
                .collect(Collectors.toList());
    }

}
