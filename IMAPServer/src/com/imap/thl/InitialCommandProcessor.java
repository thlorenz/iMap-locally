package com.imap.thl;

public class InitialCommandProcessor extends CommandProcessor {

    private static final String USERNAME = "imapnice";
    private static final String PASSWORD = "niceimap";
    private String _username = null;
    private String _password = null;

    @Override
    public CommandResponse processCommand(String commandWithTag) {
        super.processCommand(commandWithTag);

        System.out.println("Tag " + tag + "Command " + command);
        Boolean isAuthorized = false;
        if (command.startsWith("LOGIN")) {
            extractCredentials();
            isAuthorized = validateAuthorization();
            if (isAuthorized) {
                return new CommandResponse(tag + " Logged in.", this);
            } else {
                String msg = " Invalid username (" + _username + ") and/or password";
                return new CommandResponse(tag + msg, this);
            }
        }
        else
            return new CommandResponse(tag + " Please login first!", this);
    }

    private void extractCredentials() {
        String [] words = command.split(" ");
        if (words.length < 3) {
            _username = _password = null;
        } else {
            _username = words[1];
            _password = words[2];
        }
    }

    private Boolean validateAuthorization() {
        System.out.println("Authorizing user: " + _username + " password: " + _password);

        if (_username == null || _password == null) return false;

        return _username.equals(USERNAME) && _password.equals(PASSWORD);
    }

}

