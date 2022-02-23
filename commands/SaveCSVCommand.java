package commands;
import store.*;
import java.io.IOException;

public class SaveCSVCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.SAVECSV);
    }

    @Override
    public void execute(String[] args) throws IOException {
        st.writeCSV(args[0]);
    }
}