package commands;
import store.*;
import java.io.IOException;
import java.util.ArrayList;

public class ListProductByManufacturerCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.LISTPRODUCTSBYMANUFACTURER);
    }

    @Override
    public void execute(String[] args) throws IOException {
        // Get an array list of products with that manufacturer, and print each of them
        ArrayList<Product> products = st.getProductsByManufacturer(new Manufacturer(args[0]));
        for(Product p : products) {
            printProduct(p);
        }
    }

    //Auxiliary method for printing a product, different from its toString() method
    private void printProduct(Product p) throws IOException {
        Terminal.getWriter().write(p.getUniqueId() + ',' + p.getName() + ','
                + p.getManufacturer() + ',' + st.getCurrency().getSymbol()
                + p.getPrice() + ',' + p.getQuantity() + newL);
    }
}