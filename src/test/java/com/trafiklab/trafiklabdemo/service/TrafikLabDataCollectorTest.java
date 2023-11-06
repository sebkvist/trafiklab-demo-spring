package com.trafiklab.trafiklabdemo.service;

import com.trafiklab.trafiklabdemo.model.BusStop;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.fasterxml.jackson.databind.type.LogicalType.Array;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class TrafikLabDataCollectorTest {


    TrafikLabDataCollector trafikLabDataCollector = new TrafikLabDataCollector();

    @Test
    void getTopTenLinesList() {
        //Arrange
        BusStop busStop1 = BusStop.of("line1", "direction", "stop");
        BusStop busStop2 = BusStop.of("line2", "direction", "stop");
        List<BusStop> list = List.of(busStop1, busStop2);
        BusStop[] array = (BusStop[])list.toArray(new BusStop[0]);

        //Act
        //Assert
    }

    @Test
    void givenWhenArrayHasFiveItemsThenOutputIsFive() {
        //assertThrows();
        //Arrange
        //Act
        //Assert
    }

    @Test
    void getAllBusStopsForLine() {
        //Arrange
        //Act
        //Assert
    }
}