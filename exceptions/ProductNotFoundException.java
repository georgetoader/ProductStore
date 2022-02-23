package exceptions;

public class ProductNotFoundException extends Throwable {
    public String getMessage() {
        return "Products not found!";
    }

    public String getMessage(String name) {
        return "Currency " + name + " not found!";
    }
}
