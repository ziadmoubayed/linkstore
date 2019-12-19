package com.github.newswhip.linkstore.cli;

import com.github.newswhip.linkstore.service.LinkScoreService;
import picocli.CommandLine;
import picocli.CommandLine.*;

import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "link", mixinStandardHelpOptions = true, version = "Link Store Version 1.0",
        description = "Add Description",
        subcommands = {
                LinkAdd.class,
                LinkRemove.class,
                LinkExport.class,
                CommandLine.HelpCommand.class
        })
class LinkStore implements Runnable {

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        int exitCode = new CommandLine(new LinkStore()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        // your business logic goes here...
    }
}

// defines some commands to show in the list (option/parameters fields omitted for this demo)
@Command(name = "add", header = "Add a url to the store.")
class LinkAdd implements Runnable {

    @Parameters(index = "0", paramLabel = "url", description = "URL to add")
    private String url;

    @Parameters(index = "1", paramLabel = "score", description = "Social interactions")
    private Long score;

    @Override
    public void run() {
        LinkScoreService.INSTANCE.addLink(url, score);
    }
}

@Command(name = "remove", header = "Remove a url from the store.")
class LinkRemove implements Runnable {

    @Parameters(index = "0", paramLabel = "url", description = "URL to remove")
    private String url;

    @Override
    public void run() {
        LinkScoreService.INSTANCE.removeLink(url);
    }
}

@Command(name = "export", header = "Export a report.")
class LinkExport implements Callable<Map> {
    @Override
    public Map call() {
        return LinkScoreService.INSTANCE.getDomainStats();
    }
}