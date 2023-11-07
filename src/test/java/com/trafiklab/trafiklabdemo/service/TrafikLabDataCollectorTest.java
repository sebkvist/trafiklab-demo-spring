package com.trafiklab.trafiklabdemo.service;

import com.trafiklab.trafiklabdemo.controller.dto.TopLine;
import com.trafiklab.trafiklabdemo.model.BusStop;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TrafikLabDataCollectorTest {


    private TrafikLabDataCollector trafikLabDataCollector = new TrafikLabDataCollector();

    private BusStop[] busStopArray;
    private Integer uniqueLines;

    @BeforeEach
    void setupInput() {
        // Four busstops in line1, three in line2, two in line 3..
        BusStop busStop = BusStop.of("line1", "dir", "stop");
        BusStop busStop1 = BusStop.of("line1", "dir1", "stop1");
        BusStop busStop2 = BusStop.of("line2", "dir2", "stop2");
        BusStop busStop3 = BusStop.of("line3", "dir3", "stop3");
        BusStop busStop4 = BusStop.of("line2", "dir4", "stop4");
        BusStop busStop5 = BusStop.of("line2", "dir5", "stop5");
        BusStop busStop6 = BusStop.of("line3", "dir6", "stop6");
        BusStop busStop7 = BusStop.of("line4", "dir7", "stop7");
        BusStop busStop8 = BusStop.of("line5", "dir8", "stop8");
        BusStop busStop9 = BusStop.of("line1", "dir9", "stop9");
        BusStop busStop10 = BusStop.of("line6", "dir1", "stop10");
        BusStop busStop11 = BusStop.of("line1", "dir1", "stop11");
        List<BusStop> list = List.of(busStop,
                busStop1,
                busStop2,
                busStop3,
                busStop4,
                busStop5,
                busStop6,
                busStop7,
                busStop8,
                busStop9,
                busStop10,
                busStop11);
        busStopArray = (BusStop[])list.toArray(new BusStop[0]);
        uniqueLines = 6;
    }

    @Test
    void getTopTenLinesList() {
        //Act
        List<TopLine> topTen = trafikLabDataCollector.getTopTenLinesList(busStopArray);
        //Assert
        Assertions.assertThat(topTen.size()).isEqualTo(uniqueLines);

        Assertions.assertThat(topTen.get(0).lineName()).isEqualTo("line1");
        Assertions.assertThat(topTen.get(0).count()).isEqualTo(4L);

        Assertions.assertThat(topTen.get(1).lineName()).isEqualTo("line2");
        Assertions.assertThat(topTen.get(1).count()).isEqualTo(3L);

        Assertions.assertThat(topTen.get(2).lineName()).isEqualTo("line3");
        Assertions.assertThat(topTen.get(2).count()).isEqualTo(2L);

        Assertions.assertThat(topTen.get(3).lineName()).contains("line");
        Assertions.assertThat(topTen.get(3).count()).isEqualTo(1L);

    }

    private static Stream<Arguments> provideParams() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of("line1", 4),
                Arguments.of("line2", 3)
        );
    }
    @ParameterizedTest
    @MethodSource("provideParams")
    void getAllBusStopsForLine(String lineName, Integer number) {
        //Arrange
        //Act
        List<String> busStops = trafikLabDataCollector.getAllBusStopsForLine(busStopArray,lineName);
        //Assert
        Assertions.assertThat(busStops.size()).isEqualTo(number);
    }
}
