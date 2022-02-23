package commands;
import store.*;
import java.io.IOException;

public class SaveStoreCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.SAVESTORE);
    }

    @Override
    public void execute(String[] args) throws IOException {
        st.saveStore(args[0]);
    }
}