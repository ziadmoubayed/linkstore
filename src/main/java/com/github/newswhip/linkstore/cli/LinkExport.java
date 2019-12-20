package com.github.newswhip.linkstore.cli;

import com.github.newswhip.linkstore.service.LinkScoreService;
import picocli.CommandLine;

@CommandLine.Command(name = "export", header = "Export a report.")
public class LinkExport implements Runnable {

    @Override
    public void run() {
        var report = LinkScoreService.INSTANCE.getDomainStats();
        System.out.println(report);
    }
}
