package com.imap.thl;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.connect();
	}
}
