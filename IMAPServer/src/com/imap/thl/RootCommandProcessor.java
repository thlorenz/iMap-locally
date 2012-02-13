package com.imap.thl;

public class RootCommandProcessor extends CommandProcessor {

    private String _username = null;
    private String _password = null;

    public RootCommandProcessor(String username, String password) {
        _username = username;
        _password = password;
    }

    @Override
    public CommandResponse processCommand(String commandWithTag) {
        super.processCommand(commandWithTag);

        return new CommandResponse(commandWithTag + "__ Root", this);
    }
}
