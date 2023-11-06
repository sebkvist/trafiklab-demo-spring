package com.trafiklab.trafiklabdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseData(@JsonProperty(value="Version") String version, @JsonProperty(value="Type") String type, @JsonProperty(value="Result") BusStop[] result) {
}
