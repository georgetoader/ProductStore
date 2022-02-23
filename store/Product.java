package store;

import java.io.Serializable;
import java.util.Objects;

//Implements Serializable because this is written to a binary file by savestore
public class Product implements Serializable {
    private String uniqueId;
    private String name;
    private Manufacturer manufacturer;
    private double price;
    private int quantity;
    private String additionalInfo;
    private Discount discount;

    //Getters for all the fields
    public String getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Discount getDiscount() {
        return discount;
    }

    //Setters for all the fields
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                quantity == product.quantity &&
                Objects.equals(uniqueId, product.uniqueId) &&
                Objects.equals(name, product.name) &&
                Objects.equals(manufacturer, product.manufacturer) &&
                Objects.equals(discount, product.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, name, manufacturer, price, quantity, discount);
    }

    @Override
    public String toString() {
        return uniqueId;
    }
}