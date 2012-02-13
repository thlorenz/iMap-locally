package com.imap.thl;

public class RootCommandProcessor extends CommandProcessor {

    private String _username = null;
    private String _password = null;
    private String [] _boxes = { "Inbox", "Outbox", "Trash", "Drafts" };
    private String [] _references = { "ref1", "/ref2" };

    public RootCommandProcessor(String username, String password) {
        _username = username;
        _password = password;
    }

    @Override
    public CommandResponse processCommand(String commandWithTag) {
        super.processCommand(commandWithTag);

        if (verb.equals(Verbs.LIST)) {
            return listReferences();
        } else if (verb.equals(Verbs.SELECT)) {
            return selectBox();
        } else {
            return new CommandResponse(tag + " Unkown Command: " + verb, this);
        }
    }

    private CommandResponse listReferences() {
        if (wordNumber < 3) {
            return new CommandResponse(tag + " Not enough arguments given", this);
        } else {
            return listReferences(arg1, arg2);
        }
    }

    private CommandResponse listReferences(String box, String reference) {
        if (reference.equals("\"\"")) reference = "(\\Noselect)";
        String result = "* LIST " + reference + " " + _references[0] + "\n";
        String compl  = tag + " OK LIST completed\n";

        return new CommandResponse(result + compl, this);
    }

    private CommandResponse selectBox() {
        return new CommandResponse(tag + " OK New box selected", this);
    }
}
