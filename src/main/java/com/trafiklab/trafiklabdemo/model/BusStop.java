package com.trafiklab.trafiklabdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BusStop(@JsonProperty(value="LineNumber") String lineNumber,
                      @JsonProperty(value="DirectionCode") String directionCode,
                      @JsonProperty(value="JourneyPatternPointNumber") String journeyPatternPointNumber) {
    public static BusStop of(String lineNumber, String directionCode, String journeyPatternPointNumber) {
        return new BusStop(lineNumber, directionCode, journeyPatternPointNumber);
    }
}
