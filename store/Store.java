package store;

import exceptions.*;
import org.apache.commons.csv.*;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.util.*;
import java.io.*;
import java.util.*;

//SINGLETON Design Pattern
public class Store implements Serializable {
    private String name;
    private Currency currency; //current currency

    //Currencies, products, manufacturers and discounts existent on the store
    private final ArrayList<Currency> currencies;
    private ArrayList<Product> products;
    private final ArrayList<Manufacturer> manufacturers;
    private ArrayList<Discount> discounts;
    private static Store INSTANCE; //Reference to the unique instance of store

    private Store() {
        products = new ArrayList<>();
        manufacturers = new ArrayList<>();
        currencies = new ArrayList<>();
        discounts = new ArrayList<>();
        currency = new Currency("EUR", "€", 1.0);
        addCurrency(currency);
    }

    //Singleton method that gets the unique instance, or instantiates it
    public static Store getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Store();
        }
        return INSTANCE;
    }

    //Getters
    public Currency getCurrency() {
        return currency;
    }

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }


    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(Currency crtCurrency) {
        double conversionRate = currency.getParityToEur() / crtCurrency.getParityToEur();
        for(Product prod : products) {
            prod.setPrice(Precision.round(prod.getPrice() * conversionRate, 2));
        }
        this.currency = crtCurrency;
    }

    public void addCurrency(Currency c) {
        currencies.add(c);
    }

    /* Adds a new product in the Arraylist,
    and throws an Exception if the product already exists */
    public void addProduct(Product product) throws DuplicateProductException {
        Set<Product> uniqueProducts = new HashSet<>(products);
        if(uniqueProducts.contains(product))
            throw new DuplicateProductException();
        products.add(product);
    }

    // Tries to add a new Manufacturer, and trows an Exception if it already exists
    public void addManufacturer(Manufacturer manufacturer) throws DuplicateManufacturerException {
        Set<Manufacturer> uniqueManufactures = new HashSet<>(manufacturers);
        if(uniqueManufactures.contains(manufacturer))
            throw new DuplicateManufacturerException();
        manufacturers.add(manufacturer);
    }

    public void addDiscount(Discount d) {
        discounts.add(d);
    }

    // Searches in the currencies ArrayLists by the name of a currency
    public Currency findCurrencyByName(String name) throws CurrencyNotFoundException {
        for (Currency c : currencies) {
            if(c.getName().equals(name)) {
                return c;
            }
        }
        throw new CurrencyNotFoundException();
    }

    // Searches in the ArrayList, but by symbol
    public Currency findCurrencyBySymbol(String symbol) throws CurrencyNotFoundException {
        for (Currency c : currencies) {
            if(c.getSymbol().equals(symbol)) {
                return c;
            }
        }
        throw new CurrencyNotFoundException();
    }

    // Searches for a Discount by its type and value
    public Discount findDiscount(DiscountType type, double value) throws DiscountNotFoundException {
        for(Discount d : discounts) {
            if(d.getValue() == value && d.getDiscountType() == type) {
                return d;
            }
        }
        throw new DiscountNotFoundException();
    }

    //Searches for a products by its Unique ID
    public Product findProduct(String uid) throws ProductNotFoundException {
        for(Product p : products) {
            if(p.getUniqueId().equals(uid)) {
                return p;
            }
        }
        throw new ProductNotFoundException();
    }

    //Searches for a Manufacturer, using the equals method
    private Manufacturer findManufacturer(Manufacturer manufacturer) {
        for(Manufacturer m : manufacturers) {
            if(m.equals(manufacturer)) {
                return m;
            }
        }
        return null;
    }

    //Return an ArrayList of products distributed by a specific Manufacturer
    public ArrayList<Product> getProductsByManufacturer(Manufacturer manufacturer) {
        ArrayList<Product> productsByManufacturer = new ArrayList<>();
        for(Product product : products) {
            if(product.getManufacturer().equals(manufacturer)) {
                productsByManufacturer.add(product);
            }
        }
        return productsByManufacturer;
    }

    //Reads a CSV file and returns all the products read
    public ArrayList<Product> readCSV(String filename) throws IOException {
        ArrayList<Product> addedProducts = new ArrayList<>();
        CSVParser csvParser = new CSVParser(new FileReader(filename), CSVFormat.EXCEL);

        boolean first = true;
        for(CSVRecord row : csvParser) {
            //Skip the header
            if(first) {
                first = false;
                continue;
            }
            //Extract the relevant information from the columns
            String UID = row.get(ColumnType.UNIQUE_ID.ordinal());
            String productName = row.get(ColumnType.PRODUCT_NAME.ordinal());
            String manufacturerName = row.get(ColumnType.MANUFACTURER.ordinal());
            String price = row.get(ColumnType.PRICE.ordinal());
            String numAvailable = row.get(ColumnType.NUMBER_AVAILABLE_IN_STOCK.ordinal());

            //If a product has no price, skip it
            if(price.equals("")) continue;
            if(manufacturerName.equals("")) manufacturerName = "Not Available";
            if(numAvailable.equals("")) numAvailable = "0";

            //Instantiate a new manufacturer based on his name
            Manufacturer crtMan = new Manufacturer(manufacturerName);

            //Extract the value and the currency from the fourth column
            Pair<Double, Currency> convertedPrice = Currency.convertStringToPriceCurrency(price);

            //Convert the information to a price value and to a currency
            double value = convertedPrice.getLeft();
            Currency productCurrency;
            String inputSymbol = convertedPrice.getRight().getSymbol();
            try {
                productCurrency = findCurrencyBySymbol(inputSymbol);
            }
            catch (CurrencyNotFoundException e) {
                //If the currency is not found, print a message and abort
                System.out.println(e.getMessage(inputSymbol));
                return null;
            }

            //Calculate the correct price, based on the currency set on the store
            value *= productCurrency.getParityToEur() / currency.getParityToEur();
            value = Precision.round(value, 2);

            //Extract the quantity by removing all the non-numerical content from the column
            int quantity = Integer.parseInt(numAvailable.replaceAll("[^0-9]", ""));

            /* Try to add the manufacturer in the list, if he exists,
            then increment his countProducts */
            try {
                addManufacturer(crtMan);
            } catch (DuplicateManufacturerException e) {
                crtMan = findManufacturer(crtMan);
                if(crtMan != null)
                    crtMan.incrementProducts();
            }

            //Use the Builder design pattern to instantiate a product
            Product crtProd = new ProductBuilder()
                    .withName(productName)
                    .withUniqueId(UID)
                    .withPrice(value)
                    .withQuantity(quantity)
                    .withManufacturer(crtMan)
                    .build();

            addedProducts.add(crtProd);
        }
        return addedProducts;
    }

    //Write the products in a CSV file
    public void writeCSV(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        CSVPrinter csvPrinter = new CSVPrinter(fw, CSVFormat.EXCEL
                .withHeader("uniq_id", "product_name", "manufacturer",
                        "price", "number_available_in_stock"));
        for(Product prod : products) {
            String uid = prod.getUniqueId();
            String prodName = prod.getName();
            String manName = prod.getManufacturer().getName();
            String price = currency.getSymbol() + prod.getPrice();
            //Write a non-breakable space
            String noAvailable = "" + prod.getQuantity() + '\u00A0' + "NEW";

            csvPrinter.printRecord(uid, prodName, manName, price, noAvailable);
        }

        csvPrinter.flush();
    }

    //Gets fields from a binary file
    @SuppressWarnings("unchecked")
    public void loadStore(String filename) throws IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        try {
            currency = (Currency) in.readObject();
            products = (ArrayList<Product>) in.readObject();
            discounts = (ArrayList<Discount>) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Writes the current currency, products and discounts in a binary file
    public void saveStore(String filename) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(currency);
        out.writeObject(products);
        out.writeObject(discounts);
    }

    public void changeCurrency(Currency currency) throws CurrencyNotFoundException {
        this.currency = currency;
    }

    //Auxilary method for applying a Fixed Discount
    private double applyFixed(Discount discount, double price) throws NegativePriceException {
        double discountedPrice = Precision.round(price - discount.getValue(), 2);
        //If the Fixed Discount produces a negative price, throw an Exception
        if(discountedPrice < 0) {
            throw new NegativePriceException();
        }
        else
            return discountedPrice;
    }

    //Auxiliary method for applying a Percentage Discount
    private double applyPercentage(Discount discount, double price) {
        return Precision.round(price * (1 - discount.getValue() / 100), 2);
    }

    public void applyDiscount(Discount discount) throws DiscountNotFoundException {
        if(discount.getDiscountType() != DiscountType.FIXED_DISCOUNT)
            if(discount.getDiscountType() != DiscountType.PERCENTAGE_DISCOUNT)
                throw new DiscountNotFoundException();

        //Set the product Discount and apply it
        for(Product product : products) {
            product.setDiscount(discount);
            if(discount.getDiscountType() == DiscountType.PERCENTAGE_DISCOUNT) {
                product.setPrice(applyPercentage(discount, product.getPrice()));
            }
            else {
                //If the Discount is fixed, verify if the price remains positive
                try {
                    product.setPrice(applyFixed(discount, product.getPrice()));
                } catch (NegativePriceException e) {
                    System.out.println(e.getMessage(product.getUniqueId()));
                }
            }
        }
        discount.setAsAppliedNow();
    }

    //Iterates through a list of products and computes the sum
    public double calculateTotal(ArrayList<Product> product) {

        double totalPrice = 0;

        for(Product prod : product)
            totalPrice += prod.getPrice();

        return Precision.round(totalPrice, 2);
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", currency=" + currency +
                ", manufacturers=" + manufacturers +
                '}';
    }
}