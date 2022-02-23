package store;

import java.io.Serializable;
import java.util.Objects;

public class Manufacturer implements Serializable {
    private final String name;
    private int countProducts;

    public Manufacturer(String name) {
        this.name = name;
        this.countProducts = 1;
    }

    public String getName() {
        return name;
    }

    public void incrementProducts() {
        countProducts++;
    }

    public void decrementProducts() {
        countProducts--;
    }

    @SuppressWarnings("unused")
    public int countProducts() {
        //The products are already counted in the readCSV method from the store
        return countProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
