package exceptions;

public class NegativePriceException extends Exception{
    @Override
    public String getMessage() {
        return "Cannot apply selected discount!";
    }

    public String getMessage(String name) {
        return "Cannot apply selected discount for product " + name + "!";
    }
}
