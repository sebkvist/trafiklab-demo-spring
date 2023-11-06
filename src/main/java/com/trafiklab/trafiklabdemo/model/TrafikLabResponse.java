package com.trafiklab.trafiklabdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TrafikLabResponse(String StatusCode, String ExecutionTime, @JsonProperty(value="ResponseData") ResponseData responseData) {
}
