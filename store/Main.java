package store;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        //Read from standard input a list of commands
        Scanner myReader = new Scanner(new File("test1.in"));
        while(myReader.hasNextLine()) {
            String input_cmd = myReader.nextLine();
            //split the command and its arguments into tokens
            String[] tokens = input_cmd.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            //Store the arguments in an array;
            String cmd = tokens[0];
            String[] arguments = ArrayUtils.subarray(tokens, 1, tokens.length);
            try {
                Terminal.execute(CommandType.valueOf(cmd.toUpperCase(Locale.ROOT)), arguments);
            } catch (IOException e) {
                System.out.println("The system cannot access the specified file!");
            }
        }
        myReader.close();
    }
}