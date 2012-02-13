package com.imap.thl;

public class CommandProcessor {

	public CommandResponse processCommand(String commandWithTag) {

        // TODO: Error handling for invalid commands

		commandWithTag = commandWithTag.trim();
		int endTagIndex = commandWithTag.indexOf(' ');

		tag = commandWithTag.substring(0, endTagIndex);
		command = commandWithTag.substring(endTagIndex + 1);

		return new CommandResponse(null, null);
	}

	protected String command;
	protected String tag;
}
