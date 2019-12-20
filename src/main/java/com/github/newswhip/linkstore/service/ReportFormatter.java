package com.github.newswhip.linkstore.service;

import java.util.Collection;

@FunctionalInterface
public interface ReportFormatter<K extends Collection<String>, V> {
    K formatReport(V report);
}
