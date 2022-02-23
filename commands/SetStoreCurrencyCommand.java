package commands;
import exceptions.CurrencyNotFoundException;
import store.*;

public class SetStoreCurrencyCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.SETSTORECURRENCY);
    }

    @Override
    public void execute(String[] args) {
        //Search the currency in the store, then set it as the active one
        try {
            Currency crtCurrency = st.findCurrencyByName(args[0]);
            st.setCurrency(crtCurrency);
        } catch (CurrencyNotFoundException e) {
            System.out.println(e.getMessage(args[0]));
        }
    }
}