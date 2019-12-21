package com.github.newswhip.linkstore.service.reports.impl;

import com.github.newswhip.linkstore.common.Constants;
import com.github.newswhip.linkstore.service.LinkScoreService;
import com.github.newswhip.linkstore.service.reports.ReportGenerator;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of the report generator.
 * Takes a domain stats report and converts it into csv lines ready for presentation
 *
 */
public class CSVDomainReportGenerator extends ReportGenerator<Map<String, AbstractMap.SimpleEntry<Integer, Long>>> {

    @Override
    protected Collection<String> format(Map<String, AbstractMap.SimpleEntry<Integer, Long>> raw) {
        var list = raw.entrySet().stream()
                .map(entry -> entry.getKey() + Constants.CSV_SEPARATOR + entry.getValue().getKey() + Constants.CSV_SEPARATOR + entry.getValue().getValue());
        return Stream.concat(List.of(Constants.CSV_HEADERS).stream(), list).collect(Collectors.toList());
    }

    @Override
    protected Map<String, AbstractMap.SimpleEntry<Integer, Long>> getData() {
        return LinkScoreService.INSTANCE.getDomainStats();
    }
}
