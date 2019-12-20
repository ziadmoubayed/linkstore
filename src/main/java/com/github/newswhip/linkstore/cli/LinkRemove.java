package com.github.newswhip.linkstore.cli;

import com.github.newswhip.linkstore.service.LinkScoreService;
import picocli.CommandLine;

@CommandLine.Command(name = "remove", header = "Remove a url from the store.")
public class LinkRemove implements Runnable {

    @CommandLine.Parameters(index = "0", paramLabel = "url", description = "URL to remove")
    private String url;

    @Override
    public void run() {
        LinkScoreService.INSTANCE.removeLink(url);
    }
}
