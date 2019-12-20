package com.github.newswhip.linkstore.cli;

import picocli.CommandLine;

@CommandLine.Command(name = "quit", header = "Exits the REPL.")
public class LinkQuit implements Runnable {
    @Override
    public void run() {
        System.out.println("Good Bye!");
        System.exit(0);
    }
}
