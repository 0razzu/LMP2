package finance;


import java.util.Arrays;
import java.util.Objects;


public class FinanceReport {
    private Payment[] payments;
    private String reporterName;
    private int day, month, year;
    
    
    public FinanceReport(Payment[] payments, String reporterName, int day, int month, int year) throws FinanceException {
        setPayments(payments);
        setReporterName(reporterName);
        setDate(day, month, year);
    }
    
    
    public FinanceReport(FinanceReport financeReport) throws FinanceException {
        this(financeReport.getPayments(), financeReport.getReporterName(),
                financeReport.getDay(), financeReport.getMonth(), financeReport.getYear());
    }
    
    
    public void setPayments(Payment[] payments) throws FinanceException {
        if (payments == null)
            throw new FinanceException(FinanceErrorCode.NULL_PAYMENTS);
        
        this.payments = new Payment[payments.length];
        
        for (int i = 0; i < payments.length; i++) {
            if (payments[i] == null)
                throw new FinanceException(FinanceErrorCode.NULL_PAYMENTS);
            
            this.payments[i] = new Payment(payments[i].getName(),
                    payments[i].getDay(), payments[i].getMonth(), payments[i].getYear(), payments[i].getAmount());
        }
    }
    
    
    public void setReporterName(String reporterName) throws FinanceException {
        if ((reporterName == null) || (reporterName.equals("")))
            throw new FinanceException(FinanceErrorCode.WRONG_NAME);
    
        this.reporterName = reporterName;
    }
    
    
    public void setDate(int day, int month, int year) throws FinanceException {
        if (Calendar.wrongDate(day, month, year))
            throw new FinanceException(FinanceErrorCode.WRONG_DATE);
    
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    
    public Payment[] getPayments() {
        return payments;
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
    
    
    public String getReporterName() {
        return reporterName;
    }
    
    
    public Payment getPayment(int i) {
        return payments[i];
    }
    
    
    @Override
    public String toString() {
        StringBuilder paymentsSB = new StringBuilder();
        
        for (int i = 0; i < payments.length; i++) {
            paymentsSB.append("    ");
            paymentsSB.append(getPayment(i).toString());
            paymentsSB.append(",\n");
        }
        
        if (paymentsSB.length() > 0)
            paymentsSB.delete(paymentsSB.length() - 2, paymentsSB.length());
        
        return String.format("Автор: %s, дата: %02d.%02d.%04d, платежи:%n" + paymentsSB.toString(),
                reporterName, getDay(), getMonth(), getYear());
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinanceReport)) return false;
        FinanceReport that = (FinanceReport) o;
        return day == that.day &&
                month == that.month &&
                year == that.year &&
                Arrays.equals(payments, that.payments) &&
                reporterName.equals(that.reporterName);
    }
    
    
    @Override
    public int hashCode() {
        int result = Objects.hash(reporterName, day, month, year);
        result = 31 * result + Arrays.hashCode(payments);
        return result;
    }
}
