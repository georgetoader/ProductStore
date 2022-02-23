package exceptions;

public class DuplicateManufacturerException extends Exception{
    @Override
    public String getMessage() {
        return "Manufacturer already exists!";
    }

    public String getMessage(String name) {
        return "Manufacturer " + name + " already exists!";
    }
}
