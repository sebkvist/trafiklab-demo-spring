package com.trafiklab.trafiklabdemo.controller.dto;

import java.util.List;
import java.util.Map;

//public record  DemoResponse (Map<String, Long> topTenLines, List<String> busStopList) { }
public record  DemoResponse (List<TopLine> topTenLines, List<String> busStopList) { }
