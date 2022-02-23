package commands;
import store.CommandType;
import store.Store;
import java.io.IOException;

/* Interface implemented by the possible commands, allows
each command to be executed */
public interface TerminalCommand {
    Store st = Store.getInstance();
    String newL = System.getProperty("line.separator");

    /* checks the enum type and decides if the class implementing
    this interface can execute that command or not */
    boolean canProcess(CommandType type);

    // executes the command, by producing the required output
    void execute(String args[]) throws IOException;
}