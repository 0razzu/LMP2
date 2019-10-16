package finance;


import java.util.Objects;


public class Payment {
    private String name;
    private int day, month, year, amount;
    
    
    public Payment(String name, int day, int month, int year, int amount) throws FinanceException {
        setName(name);
        setDate(day, month, year);
        setAmount(amount);
    }
    
    
    public void setName(String name) throws FinanceException {
        if ((name == null) || (name.equals("")))
            throw new FinanceException(FinanceErrorCode.WRONG_NAME);
        
        this.name = name;
    }
    
    
    public void setDate(int day, int month, int year) throws FinanceException {
        if (Calendar.wrongDate(day, month, year))
            throw new FinanceException(FinanceErrorCode.WRONG_DATE);
        
        this.day = day;
        this.month = month;
        this.year = year;
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
        return day;
    }
    
    
    public int getMonth() {
        return month;
    }
    
    
    public int getYear() {
        return year;
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
        return day == payment.day &&
                month == payment.month &&
                year == payment.year &&
                amount == payment.amount &&
                name.equals(payment.name);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(name, day, month, year, amount);
    }
}