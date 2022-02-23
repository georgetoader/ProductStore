package commands;
import exceptions.DiscountNotFoundException;
import store.*;

public class ApplyDiscountCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.APPLYDISCOUNT);
    }

    @Override
    public void execute(String[] args) {
        Discount crtDiscount;
        //Searches for the Discount in the store, if it is not found then throw an exception
        try {
            if (args[0].equals("PERCENTAGE"))
                crtDiscount = st.findDiscount(DiscountType.PERCENTAGE_DISCOUNT,
                        Double.parseDouble(args[1]));
            else if (args[0].equals("FIXED"))
                crtDiscount = st.findDiscount(DiscountType.FIXED_DISCOUNT,
                        Double.parseDouble(args[1]));
            else return;
            st.applyDiscount(crtDiscount);
        }
        catch (DiscountNotFoundException e){
            System.out.println(e.getMessage(args[0] + " " + args[1]));
        }
    }
}