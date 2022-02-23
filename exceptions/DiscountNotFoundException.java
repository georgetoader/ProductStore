package exceptions;

public class DiscountNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "Discount not found!";
    }

    public String getMessage(String name) {
        return "Discount " + name + " not found!";
    }
}
