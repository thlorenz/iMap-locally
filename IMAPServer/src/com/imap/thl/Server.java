package com.imap.thl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final String HOST = "localhost";
	private static final int PORT = 3000; // 143 gives permission denied
	
	private ServerSocket serverSocket = null;
	
	public void connect() throws IOException {
		connect(HOST, PORT);
	}
	
	public void connect(String host, int port) throws IOException {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Could not listen on " + host + ":" + port);
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println("Server listening on " + host + ":" + port);
		
		acceptClient();
	}
	
	private void acceptClient() throws IOException {
		Socket clientSocket = null;
		try {
            // accept waits until client starts up and requests a connection
            // accept returns new socket bound to local port and remote address
            // and port set to that of client
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
            e.printStackTrace();
            System.exit(1);
		}
		
		InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(isr);

        String inputLine, outputLine;
		
		IMAPProtocol protocol = new IMAPProtocol();
		
		// Communicate with client until he says bye
        while ((inputLine = in.readLine()) != null) {
            outputLine = protocol.processCommand(inputLine);
            out.println(outputLine);
            if (outputLine.equals("DONE"))
                break;
        }

		
	}
	

}
