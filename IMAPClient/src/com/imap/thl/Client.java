package com.imap.thl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

public class Client {
	private static final String HOST = "localhost";
	private static final int PORT = 3000; // 143;
	private Socket clientSocket = null;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private int tagNumber = 0;

	public void connect() throws IOException {
		connect(HOST, PORT);
	}

	public void connect(String host, int port) throws IOException {
		try {
			SocketFactory socketFactory = SocketFactory.getDefault();
			clientSocket = socketFactory.createSocket(host, port);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + host);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: " + host);
			System.exit(1);
		}

		log("Connected to " + host + " at port " + port);
		readResponse();
	}

	public boolean isConnected() {
		return clientSocket != null && clientSocket.isConnected();
	}

	public void disconnect() throws IOException {
		if (!isConnected())
			throw new IllegalStateException("Not connected to a host");

		clientSocket.close();
		in.close();
		out.close();

		log("Disconnected from the host");
	}

	public String readResponse() throws IOException {
		String res = in.readLine();
		
		if (res == null) return null;
		
		if (res.startsWith("-ERR"))
			throw new RuntimeException("Server has returned an error: " + res.replaceFirst("-ERR ", ""));
	
		log("Server: " + res);
		return res;
	}

	public void sendCommand(String command) throws IOException {
		
		log ("---------------------------\n");
		
		String tag = "a" + tagNumber++;
		String req = tag + " " + command;
		log("Client: " + req);
		
		out.write(req + "\r\n");
		out.flush();
		
		// Read responses until we get final response for tag
		while(!readResponse().startsWith(tag)) { }
	}
	
	public void login(String username, String password) throws IOException {
		sendCommand("LOGIN  " + username + " " + password);
	}

	public void logout() throws IOException {
		sendCommand("LOGOUT");
	}

	private static void log(String msg) {
		System.out.println(msg);
	}
}
