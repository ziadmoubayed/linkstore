package com.github.newswhip.linkstore.cli;

import com.github.newswhip.linkstore.service.LinkScoreService;
import picocli.CommandLine;

// defines some commands to show in the list (option/parameters fields omitted for this demo)
@CommandLine.Command(name = "add", header = "Add a url to the store.")
public class LinkAdd implements Runnable {

    @CommandLine.Parameters(index = "0", paramLabel = "url", description = "URL to add")
    private String url;

    @CommandLine.Parameters(index = "1", paramLabel = "score", description = "Social interactions")
    private Long score;

    @Override
    public void run() {
        LinkScoreService.INSTANCE.addLink(url, score);
    }
}
