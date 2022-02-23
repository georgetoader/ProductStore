package commands;
import store.*;
import java.io.IOException;
import java.util.ArrayList;

public class ListDiscountsCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.LISTDISCOUNTS);
    }

    @Override
    public void execute(String[] args) throws IOException {
        //Write to file each discount from the store
        ArrayList<Discount> discounts = st.getDiscounts();
        for(Discount d : discounts) {
            Terminal.getWriter().write(d.toString() + newL);
        }
    }
}