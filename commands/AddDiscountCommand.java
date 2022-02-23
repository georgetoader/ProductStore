package commands;
import store.*;

public class AddDiscountCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.ADDISCOUNT);
    }

    @Override
    public void execute(String[] args) {
        Discount crtDiscount;
        //Finds the correct Discount type and appends it to its ArrayList from store
        if(args[0].equals("PERCENTAGE"))
            crtDiscount = new Discount(args[2], DiscountType.PERCENTAGE_DISCOUNT,
                    Double.parseDouble(args[1]));
        else if(args[0].equals("FIXED"))
            crtDiscount = new Discount(args[2], DiscountType.FIXED_DISCOUNT,
                    Double.parseDouble(args[1]));
        else return;

        st.addDiscount(crtDiscount);
    }
}