package commands;
import store.*;
import java.io.IOException;
import java.util.ArrayList;

public class ListManufacturersCommand implements TerminalCommand {

    @Override
    public boolean canProcess(CommandType type) {
        return type.equals(CommandType.LISTMANUFACTURERS);
    }

    @Override
    public void execute(String[] args) throws IOException {
        // Write to file every found manufacturer
        ArrayList<Manufacturer> manufacturers = st.getManufacturers();
        for(Manufacturer man : manufacturers) {
            Terminal.getWriter().write(man.toString() + newL);
        }
    }
}