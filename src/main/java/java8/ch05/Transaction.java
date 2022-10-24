package java8.ch05;

public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader tradeer, int year, int value) {
        this.trader = tradeer;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" +
                "tradeer=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
