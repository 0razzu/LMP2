package finance;


import java.util.Arrays;


public class FinanceReport {
    private Payment[] payments;
    private String reporterName;
    private int[] date;
    
    
    public FinanceReport(Payment[] payments, String reporterName, int day, int month, int year) throws FinanceException {
        setPayments(payments);
        setReporterName(reporterName);
        date = new int[3];
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
    
    
    public Payment[] getPayments() {
        return payments;
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
        return Arrays.equals(payments, that.payments) &&
                reporterName.equals(that.reporterName) &&
                Arrays.equals(date, that.date);
    }
}
