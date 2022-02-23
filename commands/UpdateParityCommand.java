package commands;
import exceptions.CurrencyNotFoundException;
import store.*;

public class UpdateParityCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.UPDATEPARITY);
    }

    @Override
    public void execute(String[] args) {
        //Searches for the currency in the store, then updates its parity
        try {
            Currency crtCurrency = st.findCurrencyByName(args[0]);
            crtCurrency.updateParity(Double.parseDouble(args[1]));
        } catch (CurrencyNotFoundException e) {
            System.out.println(e.getMessage(args[0]));
        }
    }
}