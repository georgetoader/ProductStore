package exceptions;

public class DuplicateProductException extends Exception{
    @Override
    public String getMessage() {
        return "Product already exists!";
    }

    public String getMessage(String name) {
        return "Product " + name + " already exists!";
    }
}
