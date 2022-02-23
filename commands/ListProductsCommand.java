package commands;
import store.*;
import java.io.IOException;
import java.util.ArrayList;

public class ListProductsCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.LISTPRODUCTS);
    }

    @Override
    public void execute(String[] args) throws IOException {
        // Write all the products to the file
        ArrayList<Product> products = st.getProducts();
        for(Product prod : products) {
            printProduct(prod);
        }
    }

    //Auxiliary method for printing a product, different from its toString() method
    private void printProduct(Product p) throws IOException {
        Terminal.getWriter().write(p.getUniqueId() + ',' + p.getName() + ','
                + p.getManufacturer() + ',' + st.getCurrency().getSymbol()
                + p.getPrice() + ',' + p.getQuantity() + newL);
    }
}