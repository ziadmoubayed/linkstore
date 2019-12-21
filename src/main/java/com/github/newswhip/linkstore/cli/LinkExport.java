package com.github.newswhip.linkstore.cli;

import com.github.newswhip.linkstore.service.reports.ReportGenerator;
import com.github.newswhip.linkstore.service.reports.impl.CSVDomainReportGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

@CommandLine.Command(name = "export", header = "Export a report.")
public class LinkExport implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(LinkExport.class);

    @CommandLine.Option(names = {"-f", "--file"}, description = "/tmp/export.csv")
    private File dest;


    private final ReportGenerator reportGenerator;

    public LinkExport() {
        this.reportGenerator = new CSVDomainReportGenerator();
    }


    @Override
    public void run() {
        //Generates the report
        var report = this.reportGenerator.getReport();
        /**
         * If destination file not specified.
         * Print to output stream.
         */
        if (dest == null) {
            print(report);
            return;
        }
        try {
            write(report, dest);
        } catch (IOException e) {
            logger.error("Error writing report to: " + dest, e);
        }
    }

    /**
     * Prints the formatted line collection, line by line.
     *
     * @param formattedReport report formatted and ready for print
     */
    private void print(Collection<String> formattedReport) {
        formattedReport.forEach(System.out::println);
    }

    /**
     * Writes the lines to the destination file.
     * Fails If File already exists or directory does not exist.
     *
     * @param formattedReport report formatted and ready for write
     * @param dest destination file
     * @throws IOException if writing fails
     */
    private void write(Collection<String> formattedReport, File dest) throws IOException {
        Files.write(dest.toPath(), formattedReport, StandardOpenOption.CREATE_NEW);
    }
}
