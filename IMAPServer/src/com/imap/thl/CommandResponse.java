package com.imap.thl;

public class CommandResponse {
    private String _reply;
    private CommandProcessor _nextCommandProcessor;

    public CommandResponse(String reply, CommandProcessor nextCommandProcessor) {
        _reply = reply;
        _nextCommandProcessor = nextCommandProcessor;
    }

    public String getReply() {
        return _reply;
    }

    public CommandProcessor getNextCommandProcessor() {
        return _nextCommandProcessor;
    }

}
