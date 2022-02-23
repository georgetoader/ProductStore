package commands;
import store.*;
import java.io.IOException;

public class GetStoreCurrencyCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.GETSTORECURRENCY);
    }

    @Override
    public void execute(String[] args) throws IOException {
        // Print to file the current currency available
        Currency crtCurrency = st.getCurrency();
        Terminal.getWriter().write(crtCurrency.getSymbol() + " " + crtCurrency.getName() + newL);
    }
}