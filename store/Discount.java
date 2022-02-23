package store;

import java.io.Serializable;
import java.time.LocalDateTime;

//Implements Serializable because this is written to a binary file by savestore
public class Discount implements Serializable {
    private final String name;
    private final DiscountType discountType;
    private double value;
    private LocalDateTime lastDateApplied;

    public Discount(String name, DiscountType discountType, double value) {
        this.name = name;
        this.discountType = discountType;
        this.value = value;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    //set the Discount as applied, and set the nanoseconds to 0
    public void setAsAppliedNow() {
        lastDateApplied = LocalDateTime.now().withNano(0);
    }

    @Override
    public String toString() {
        String result =  discountType + " " + value + " " + name + " ";
        if(lastDateApplied == null) result += "Not Applied";
        else result += lastDateApplied;
        return result;
    }
}