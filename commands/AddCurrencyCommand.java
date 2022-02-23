package commands;
import store.*;
import java.io.IOException;

public class AddCurrencyCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.ADDCURRENCY);
    }

    @Override
    public void execute(String[] args) throws IOException {
        Store st = Store.getInstance();

        //Instantiate a new currency and add it to the ArrayList from store
        Currency crtCurrency = new Currency(args[0], args[1], Double.parseDouble(args[2]));
        st.addCurrency(crtCurrency);
    }
}