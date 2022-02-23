package commands;
import exceptions.ProductNotFoundException;
import store.*;
import java.io.IOException;

public class ShowProductCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.SHOWPRODUCT);
    }

    @Override
    public void execute(String[] args) throws IOException {
        /*Search the product in the store, then print it. If it is not found,
        throw an exception */
        try {
            Product prod = st.findProduct(args[0]);
            printProduct(prod);
        } catch (ProductNotFoundException e) {
            e.getMessage(args[0]);
        }
    }

    //Auxiliary method for printing a product, different from its toString() method
    private void printProduct(Product p) throws IOException {
        Terminal.getWriter().write(p.getUniqueId() + ',' + p.getName() + ','
                + p.getManufacturer() + ',' + st.getCurrency().getSymbol()
                + p.getPrice() + ',' + p.getQuantity() + newL);
    }
}