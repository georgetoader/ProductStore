package commands;
import exceptions.DuplicateProductException;
import store.*;
import java.io.IOException;
import java.util.ArrayList;

public class LoadCSVCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.LOADCSV);
    }

    @Override
    public void execute(String[] args) throws IOException {
        //Obtain the newly added products, then add them into the store
        ArrayList<Product> addedProducts = st.readCSV(args[0]);
        if(addedProducts == null) {
            System.out.println("Wrong .csv file name!");
            return;
        }
        for (Product prod : addedProducts) {
            try {
                st.addProduct(prod);
            } catch (DuplicateProductException e) {
                //If the product is a duplicate, remove it from manufacturer's product count
                Manufacturer crtMan = prod.getManufacturer();
                if (crtMan != null)
                    crtMan.decrementProducts();
            }
        }
    }
}