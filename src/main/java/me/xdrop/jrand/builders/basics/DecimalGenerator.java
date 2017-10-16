package me.xdrop.jrand.builders.basics;

import me.xdrop.jrand.Generator;

import java.math.BigDecimal;

public class DecimalGenerator extends Generator<String> {

    private double min;
    private double max;
    private int digits;
    private boolean roundUp;

    public DecimalGenerator() {
        this.roundUp = true;
        this.max = 100;
    }

    /**
     * Set the minimum value (inclusive)
     *
     * @param min The minimum value
     * @return
     */
    public DecimalGenerator min(double min) {
        this.min = min;
        return this;
    }

    /**
     * Sets the maximum value (inclusive)
     *
     * @param max The maximum value
     * @return
     */
    public DecimalGenerator max(double max) {
        this.max = max;
        return this;
    }

    /**
     * Set a min/max range
     *
     * @param min Minimum value to be returned (inclusive)
     * @param max Maximum value to be returned (inclusive)
     * @return
     */
    public DecimalGenerator range(double min, double max) {
        this.max = max;
        this.min = min;
        return this;
    }

    /**
     * Sets the number of digits to return
     *
     * @param digits Number of digits
     * @return
     */
    public DecimalGenerator digits(int digits) {
        this.digits = digits;
        return this;
    }

    /**
     * Set whether to round up or down
     *
     * @param roundUp True for round up, false for round down
     * @return
     */
    public DecimalGenerator roundUp(boolean roundUp) {
        this.roundUp = roundUp;
        return this;
    }


    public BigDecimal genAsDecimal() {
        double rand = new DoubleGenerator().min(this.min).max(this.max).gen();
        BigDecimal decimal;
        if (digits != 0) {
            if (roundUp) {
                decimal = BigDecimal.valueOf(rand).setScale(digits, BigDecimal.ROUND_UP);
            } else {
                decimal = BigDecimal.valueOf(rand).setScale(digits, BigDecimal.ROUND_DOWN);
            }
        } else {
            decimal = BigDecimal.valueOf(rand);
        }

        return decimal;
    }

    public String gen() {
        return genAsDecimal().toString();
    }
}
