package chapter2kiosk;

import java.util.function.Function;

public enum Discount {
    NATIONAL_MERIT_RECIPIENT(1, price -> price * 0.9),
    SOLDIER(2, price -> price * 0.95),
    STUDENT(3, price -> price * 0.97),
    NORMAL(4, price -> price);

    private final int symbol;
    private final Function<Double, Double> calc;
//        private final Function<T, R> function;

    Discount(int symbol) {
        this.symbol = symbol;
        this.calc = null;
    }

    Discount(int symbol, Function<Double, Double> calc) {
        this.symbol = symbol;
        this.calc = calc;
    }

    public Double apply(Double number1) {
        return calc.apply(number1);
    }

    public int getSymbol() {
        return this.symbol;
    }
}
