package com.imap.thl;

public class IMAPProtocol {


	public String processCommand(String command) {
		log(command);
		return command;
	}

    public String getReadyMessage(String url) {
        return "* OK Gimap ready for requests from " + url;
    }

	private static void log(String msg) {
		System.out.println(msg);
	}
}
