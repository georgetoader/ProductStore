package store;

import commands.*;
import exceptions.CommandNotFoundException;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import static java.util.Arrays.asList;

//Class used for executing commands
//STRATEGY Design Pattern
public class Terminal {

    /* The writer is initialised in a static context and used for printing
    the resulting output in the file result.out */
    private static FileWriter myWriter;

    static {
        try {
            myWriter = new FileWriter("result.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileWriter getWriter() {
        return myWriter;
    }

    /* Static method used for selecting a corresponding class, based on the type of the command.
    The chosen class implements the TerminalCommand interface, and thus, is able to execute it. */
    private static TerminalCommand selectTerminalCommand(CommandType type) throws CommandNotFoundException {
        List<TerminalCommand> commands = asList(new AddCurrencyCommand(), new AddDiscountCommand(),
                new ApplyDiscountCommand(), new CalculateTotalCommand(),
                new GetStoreCurrencyCommand(), new ListCurrenciesCommand(),
                new ListDiscountsCommand(), new ListManufacturersCommand(),
                new ListProductByManufacturerCommand(),  new ListProductsCommand(),
                new LoadCSVCommand(), new LoadStoreCommand(), new SaveCSVCommand(),
                new SaveStoreCommand(), new SetStoreCurrencyCommand(), new ShowProductCommand(),
                new UpdateParityCommand(), new ExitCommand());

        // Iterate through all possible commands and verify which one can process the input
        for (TerminalCommand cmd : commands) {
            if (cmd.canProcess(type)) {
                return cmd;
            }
        }

        throw new CommandNotFoundException();
    }

    /* Method that chooses the correct Terminal Command and calls its execute() method,
    or prints a message if the input command is not found */
    public static void execute(CommandType command, String[] args) throws IOException {
        TerminalCommand cmd;
        try {
            cmd = selectTerminalCommand(command);
            cmd.execute(args);
        } catch (CommandNotFoundException e) {
            e.getMessage(command.toString());
        }
    }
}