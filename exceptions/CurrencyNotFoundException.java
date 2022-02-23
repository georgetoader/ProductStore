package exceptions;

public class CurrencyNotFoundException extends Exception{

    @Override
    public String getMessage() {
        return "Currency not found!";
    }

    public String getMessage(String name) {
        return "Currency " + name + " not found!";
    }
}