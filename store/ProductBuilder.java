package store;

//BUILDER Design Pattern
//Class used for building Product objects
public class ProductBuilder {

    private final Product product = new Product();

    public ProductBuilder withUniqueId(String uniqueId) {
        product.setUniqueId(uniqueId);
        return this;
    }

    public ProductBuilder withName(String name) {
        product.setName(name);
        return this;
    }

    public ProductBuilder withManufacturer(Manufacturer manufacturer) {
        product.setManufacturer(manufacturer);
        return this;
    }

    public ProductBuilder withPrice(double price) {
        product.setPrice(price);
        return this;
    }

    public ProductBuilder withQuantity(int quantity) {
        product.setQuantity(quantity);
        return this;
    }

    public Product build() {
        return product;
    }
}
