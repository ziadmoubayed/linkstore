package com.github.newswhip.linkstore;

import picocli.CommandLine;

import java.util.concurrent.Callable;

/*
 *
 * Example:
 * 	add --url=http://link.com/123 --score=50
 */
@CommandLine.Command(name="add", description = "add a URL with an associated social score")
class Add  implements Callable<Void> {
    @CommandLine.Option(names = "--url", required = true, description = "the url to import")
    String link;

    @CommandLine.Option(names = "--score", required = true, description = "social score associated with the url")
    Long score;

    @Override
    public Void call() throws Exception {
        return null;
    }
}


    public class LinkStoreCli implements Callable<Void> {

    public static void main(String[] args) throws Exception {
        CommandLine commandLine = new CommandLine(new LinkStoreCli());
        commandLine.parseWithHandler(new CommandLine.RunLast(), args);
    }

    @Override
    public Void call() {
        CommandLine.usage(this, System.err);
        return null;
    }
}
