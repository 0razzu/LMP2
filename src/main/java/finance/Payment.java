package finance;


import java.util.Arrays;
import java.util.Objects;


public class Payment {
    private String name;
    private int[] date;
    private int amount;
    
    
    public Payment(String name, int day, int month, int year, int amount) throws FinanceException {
        setName(name);
        date = new int[3];
        setDate(day, month, year);
        setAmount(amount);
    }
    
    
    public void setName(String name) throws FinanceException {
        if ((name == null) || (name == ""))
            throw new FinanceException(FinanceErrorCode.WRONG_NAME);
        
        this.name = name;
    }
    
    
    public void setDate(int day, int month, int year) throws FinanceException {
        if ((year <= 0) || (month <= 0) || (month > 12) || (day <= 0) || ((day > 31) ||
                (day > 30) &&
                        ((month == 4) || (month == 6) || (month == 9) || (month == 11)) ||
                (month == 2) &&
                        ((day > 29) ||
                                ((day > 28) && !((year % 400 == 0) || (year % 100 != 0) && (year % 4 == 0))))))
            throw new FinanceException(FinanceErrorCode.WRONG_DATE);
        
        date[0] = day;
        date[1] = month;
        date[2] = year;
    }
    
    
    public void setAmount(int amount) throws FinanceException {
        if (amount == 0)
            throw new FinanceException(FinanceErrorCode.WRONG_AMOUNT);
        
        this.amount = amount;
    }
    
    
    public String getName() {
        return name;
    }
    
    
    public int getDay() {
        return date[0];
    }
    
    
    public int getMonth() {
        return date[1];
    }
    
    
    public int getYear() {
        return date[2];
    }
    
    
    public int getAmount() {
        return amount;
    }
    
    
    @Override
    public String toString() {
        return String.format("Плательщик: %s, дата: %02d.%02d.%04d, сумма: %d ₽ %02d коп.",
                getName(), getDay(), getMonth(), getYear(), getAmount() / 100, getAmount() % 100);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return amount == payment.amount &&
                name.equals(payment.name) &&
                Arrays.equals(date, payment.date);
    }
    
    
    @Override
    public int hashCode() {
        int result = Objects.hash(name, amount);
        result = 31 * result + Arrays.hashCode(date);
        return result;
    }
}