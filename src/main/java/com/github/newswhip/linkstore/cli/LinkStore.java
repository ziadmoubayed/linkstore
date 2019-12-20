package com.github.newswhip.linkstore.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.Scanner;

@Command(name = "links", mixinStandardHelpOptions = true, version = "Link Store Version 1.0",
        description = "Add Description",
        subcommands = {
                LinkAdd.class,
                LinkRemove.class,
                LinkExport.class,
                CommandLine.HelpCommand.class,
                LinkQuit.class
        })
public class LinkStore implements Runnable {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in).useDelimiter("\n");						// So that prompt shows once each time
        new CommandLine(new LinkStore()).execute("--help".split(" "));
        String shPrompt = "cli> ";
        System.out.print(shPrompt);
        while (true) {
            if (input.hasNext()) {
                String cmd = input.next();
                new CommandLine(new LinkStore()).execute(cmd.split(" "));
                System.out.print(shPrompt);
            }
        }
    }

    @Override
    public void run() {
        // your business logic goes here...
    }
}

