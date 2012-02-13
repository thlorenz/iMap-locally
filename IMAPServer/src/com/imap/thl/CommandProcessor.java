package com.imap.thl;

public class CommandProcessor {

	public CommandResponse processCommand(String commandWithTag) {

        // TODO: Error handling for invalid commands

		commandWithTag = commandWithTag.trim();

        // Look away! Really stupid way to extract command and args
        String [] words = commandWithTag.split(" ");
        wordNumber = words.length;
        if (wordNumber > 1) {
            tag = words[0];
            verb = words[1];
        }

        if (wordNumber > 2) arg1 = words[2];
        if (wordNumber > 3) arg2 = words[3];
        if (wordNumber > 4) arg3 = words[4];

		return new CommandResponse(null, null);
	}

	protected String tag = null;
	protected String verb = null;
    protected String arg1 = null;
    protected String arg2 = null;
    protected String arg3 = null;
    protected int wordNumber = 0;
}
