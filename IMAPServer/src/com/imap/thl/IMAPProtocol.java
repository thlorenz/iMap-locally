package com.imap.thl;

public class IMAPProtocol {
	
	
	public String processCommand(String command) {
		log(command);
		return command;
	}
	
	private static void log(String msg) {
		System.out.println(msg);
	}
}
