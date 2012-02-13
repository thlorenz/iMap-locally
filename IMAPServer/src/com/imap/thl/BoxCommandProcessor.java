package com.imap.thl;

public class BoxCommandProcessor extends CommandProcessor {

    private String _username = null;
    private String _password = null;
    private String _box = null;

    private String [] _references = null;

    public BoxCommandProcessor(String username, String password, String box) {
        _username = username;
        _password = password;
        _box = box;

        _references = new String [] { _box + "/ref1", _box + "/ref2" };
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

    private CommandResponse listReferences(String box, String reference) {
        if (reference.equals("\"\"")) reference = "(\\Noselect)";
        String result = "* LIST " + reference + " " + _references[0] + "\n";
        String compl  = tag + " OK LIST completed\n";

        return new CommandResponse(result + compl, this);
    }

    private CommandResponse listReferences() {
        if (wordNumber < 3) {
            return new CommandResponse(tag + " Not enough arguments given", this);
        } else {
            return listReferences(arg1, arg2);
        }
    }

    private CommandResponse selectBox() {
        String result =  "* 172 EXISTS\n"
                       + "* 1 RECENT\n"
                       + "* OK [UNSEEN 12] Message 12 is first unseen\n"
                       + "* OK [UIDVALIDITY 3857529045] UIDs valid\n"
                       + "* OK [UIDNEXT 4392] Predicted next UID\n"
                       + "* FLAGS (\\Answered \\Flagged \\Deleted \\Seen \\Draft)\n"
                       + "* OK [PERMANENTFLAGS (\\Deleted \\Seen \\*)] Limited\n";

        String compl = tag + " OK [READ-WRITE] SELECT completed\n";

        BoxCommandProcessor boxProc = new BoxCommandProcessor(_username, _password, arg1);
        return new CommandResponse(result + compl, boxProc);
    }
}
