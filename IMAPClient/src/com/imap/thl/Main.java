package com.imap.thl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		Client client = new Client();
		
		client.connect();
		client.login("imapnice@gmail.com", "niceimap");

		/*
		client.sendCommand("STATUS INBOX (messages)");
		client.sendCommand("STATUS INBOX (unseen)");
		client.sendCommand("SELECT INBOX");
		*/

		client.sendCommand("LIST \"\" *");
		client.sendCommand("SELECT Inbox");

		startRepl(client);

		client.logout();
		client.disconnect();
	}

	private static void startRepl(Client client) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("> ");

		String command = null;
		while (!(command = in.readLine()).equals(":q"))
		{
			client.sendCommand(command);
			System.out.print("> ");
		}
	}


}
