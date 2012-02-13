package com.imap.thl;

public class InitialCommandProcessor extends CommandProcessor {

    private static final String USERNAME = "imapnice";
    private static final String PASSWORD = "niceimap";
    private String _username = null;
    private String _password = null;

    @Override
    public CommandResponse processCommand(String commandWithTag) {
        super.processCommand(commandWithTag);

        Boolean isAuthorized = false;

        if (verb.equals(Verbs.LOGIN)) {
            extractCredentials();
            isAuthorized = validateAuthorization();

            if (isAuthorized) {
                RootCommandProcessor rootProc = new RootCommandProcessor(_username, _password);
                return new CommandResponse(tag + " Successfully logged in.", rootProc);
            } else {
                String msg = " Invalid username (" + _username + ") and/or password " + _password;
                return new CommandResponse(tag + msg, this);
            }
        }
        else
            return new CommandResponse(tag + " Please login first!", this);
    }

    private void extractCredentials() {
        if (wordNumber < 3) {
            _username = _password = null;
        } else {
            _username = arg1;
            _password = arg2;
        }
    }

    private Boolean validateAuthorization() {
        System.out.println("Authorizing user: " + _username + " password: " + _password);

        if (_username == null || _password == null) return false;

        return _username.equals(USERNAME) && _password.equals(PASSWORD);
    }

}

