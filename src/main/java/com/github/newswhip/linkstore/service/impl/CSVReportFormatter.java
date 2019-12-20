package com.github.newswhip.linkstore.service.impl;

import com.github.newswhip.linkstore.common.Constants;
import com.github.newswhip.linkstore.service.ReportFormatter;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Formats report into lines separated by space.
 * eg:
 */
public class CSVReportFormatter implements ReportFormatter<List<String>, Map<String, AbstractMap.SimpleEntry<Integer, Long>>> {

    @Override
    public List<String> formatReport(Map<String, AbstractMap.SimpleEntry<Integer, Long>> report) {
        var list = report.entrySet().stream()
                .map(entry -> entry.getKey() + Constants.CSV_SEPARATOR + entry.getValue().getKey() + Constants.CSV_SEPARATOR + entry.getValue().getValue());
        return Stream.concat(List.of(Constants.CSV_HEADERS).stream(), list).collect(Collectors.toList());
    }
}
