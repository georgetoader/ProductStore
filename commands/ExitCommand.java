package commands;
import store.*;
import java.io.IOException;

public class ExitCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.QUIT) |
                type.equals(CommandType.EXIT);
    }

    @Override
    public void execute(String[] args) throws IOException {
        //Close the output file and exit the application
        Terminal.getWriter().close();
        System.exit(0);
    }
}