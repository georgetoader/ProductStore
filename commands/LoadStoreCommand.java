package commands;
import store.*;
import java.io.IOException;

public class LoadStoreCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.LOADSTORE);
    }

    @Override
    public void execute(String[] args) throws IOException {
        st.loadStore(args[0]);
    }
}