package commands;
import exceptions.ProductNotFoundException;
import store.*;
import java.io.IOException;
import java.util.ArrayList;

public class CalculateTotalCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.CALCULATETOTAL);
    }

    @Override
    public void execute(String[] args) {
        // Create an ArrayList with the products of UIDs specified in the arguments
        ArrayList<Product> shoppingList = new ArrayList<>();
        try {
            for (String s : args) {
                shoppingList.add(st.findProduct(s));
            }
            //Call the corresponding method from the store to compute the sum
            Terminal.getWriter().write(st.getCurrency().getSymbol() +
                    st.calculateTotal(shoppingList) + newL);
        }
        catch(ProductNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}