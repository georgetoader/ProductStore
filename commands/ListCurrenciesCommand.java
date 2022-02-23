package commands;
import store.*;
import java.io.IOException;

public class ListCurrenciesCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.LISTCURRENCIES);
    }

    @Override
    public void execute(String[] args) throws IOException {
        // Write to file each found currency
        for(Currency c : st.getCurrencies()) {
            Terminal.getWriter().write(c.toString() + newL);
        }
    }
}