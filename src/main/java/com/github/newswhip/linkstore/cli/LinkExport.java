package com.github.newswhip.linkstore.cli;

import com.github.newswhip.linkstore.service.LinkScoreService;
import com.github.newswhip.linkstore.service.ReportFormatter;
import com.github.newswhip.linkstore.service.impl.CSVReportFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.AbstractMap;
import java.util.Map;

@CommandLine.Command(name = "export", header = "Export a report.")
public class LinkExport implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(LinkExport.class);

    @CommandLine.Option(names = {"-f", "--file"}, description = "/tmp/export.csv")
    private File dest;

    @Override
    public void run() {
        var report = LinkScoreService.INSTANCE.getDomainStats();
        if (dest == null) {
            print(report, new CSVReportFormatter());
            return;
        }
        try {
            write(report, dest, new CSVReportFormatter());
        } catch (IOException e) {
            logger.error("Error writing report to: " + dest, e);
        }
    }

    private void print(Map<String, AbstractMap.SimpleEntry<Integer, Long>> report, ReportFormatter reportFormatter) {
        reportFormatter.formatReport(report).forEach(System.out::println);
    }

    private void write(Map<String, AbstractMap.SimpleEntry<Integer, Long>> report, File dest, ReportFormatter reportFormatter) throws IOException {
        var lines = reportFormatter.formatReport(report);
        Files.write(dest.toPath(), lines, StandardOpenOption.CREATE_NEW);
    }
}
