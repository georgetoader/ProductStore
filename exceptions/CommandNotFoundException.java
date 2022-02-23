package exceptions;

public class CommandNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Command not found!";
    }

    public String getMessage(String name) {
        return "Command not found: + " + name + "!";
    }
}