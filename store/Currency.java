package store;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.util.Precision;

import java.io.Serializable;

//Implements Serializable because this is written to a binary file by savestore
public class Currency implements Serializable {
    private String name;
    private String symbol;
    private double parityToEur;

    public Currency() {
    }

    public Currency(String name, String symbol, double parityToEur) {
        this.name = name;
        this.symbol = symbol;
        this.parityToEur = parityToEur;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getParityToEur() {
        return parityToEur;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void updateParity(double parityToEUR) {
        parityToEur = parityToEUR;
    }

    //Auxiliary price conversion methods
    private static double convertPrice(String price) {
        return Double.parseDouble(price.replaceAll("[^\\d.]", ""));
    }

    private static double convertPrice(String price, Currency currency) {
        //Gets only the first value, if the String is of type "14-15$"
        int end = price.indexOf('-');
        if (end != -1) price = price.substring(0, end);

        //Extract the symbol using regex
        String symbol = price.replaceAll("[\\d .,]", "");
        currency.setSymbol(symbol);

        return convertPrice(price);
    }

    //Converts a String of type "15.99$" to a pair of Double value and Currency
    public static Pair<Double, Currency> convertStringToPriceCurrency(String priceWithCurrency) {
        Currency crtCurrency = new Currency();
        Double price = convertPrice(priceWithCurrency, crtCurrency);
        return new ImmutablePair<>(price, crtCurrency);
    }

    @Override
    public String toString() {
        //Print the name and parity using at most 2 decimals
        return  name + " " + Precision.round(parityToEur, 2);
    }
}