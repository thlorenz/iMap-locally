package com.imap.thl;

public class IMAPProtocol {

    private CommandProcessor _commandProcessor = null;

    public IMAPProtocol() {
        _commandProcessor = new InitialCommandProcessor();
    }

	public String processCommand(String command) {
		log(command);

        CommandResponse res = _commandProcessor.processCommand(command);
        _commandProcessor = res.getNextCommandProcessor();

		return res.getReply();
	}

    public String getReadyMessage(String url) {
        return "* OK Gimap ready for requests from " + url;
    }

	private static void log(String msg) {
		System.out.println(msg);
	}
}
