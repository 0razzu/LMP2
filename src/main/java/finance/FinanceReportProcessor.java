package finance;


import java.util.LinkedList;


public class FinanceReportProcessor {
    public static FinanceReport getStartingWith(char c, final FinanceReport financeReport) throws FinanceException {
        if (financeReport == null)
            throw new FinanceException(FinanceErrorCode.NULL_REPORT);
        
        LinkedList<Payment> paymentsNew = new LinkedList<>();
        
        for (Payment payment: financeReport.getPayments())
            if (payment.getName().charAt(0) == c)
                paymentsNew.add(payment);
        
        return new FinanceReport((Payment[]) paymentsNew.toArray(), financeReport.getReporterName(),
                financeReport.getDay(), financeReport.getMonth(), financeReport.getYear());
    }
    
    
    public static FinanceReport getPaymentsLessThan(int amount, final FinanceReport financeReport) throws FinanceException {
        if (financeReport == null)
            throw new FinanceException(FinanceErrorCode.NULL_REPORT);
        
        LinkedList<Payment> paymentsNew = new LinkedList<>();
        
        for (Payment payment: financeReport.getPayments())
            if (payment.getAmount() < amount)
                paymentsNew.add(payment);
        
        return new FinanceReport((Payment[]) paymentsNew.toArray(), financeReport.getReporterName(),
                financeReport.getDay(), financeReport.getMonth(), financeReport.getYear());
    }
    
    
    public static int getSumOn(String date, final FinanceReport financeReport) throws FinanceException {
        if (financeReport == null)
            throw new FinanceException(FinanceErrorCode.NULL_REPORT);
        
        int sum = 0, day, month, year;
        
        try {
            day = Integer.parseInt(date.substring(0, 2));
            month = Integer.parseInt(date.substring(3, 5));
            year = Integer.parseInt(date.substring(6, date.length()));
        } catch (NumberFormatException e) {
            throw new FinanceException(FinanceErrorCode.WRONG_DATE);
        }
        
        if ((year <= 0) || (month <= 0) || (month > 12) || (day <= 0) || ((day > 31) ||
                (day > 30) &&
                        ((month == 4) || (month == 6) || (month == 9) || (month == 11)) ||
                (month == 2) &&
                        ((day > 29) ||
                                ((day > 28) && !((year % 400 == 0) || (year % 100 != 0) && (year % 4 == 0))))))
            throw new FinanceException(FinanceErrorCode.WRONG_DATE);
        
        for (Payment payment: financeReport.getPayments())
            if ((payment.getDay() == day) && (payment.getMonth() == month) && (payment.getYear() == year))
                sum += payment.getAmount();
        
        return sum;
    }
    
    
    public static String getMonthsWithoutPayments(final FinanceReport financeReport, int year) throws FinanceException {
        if (financeReport == null)
            throw new FinanceException(FinanceErrorCode.NULL_REPORT);
        
        if (year <= 0)
            throw new FinanceException(FinanceErrorCode.WRONG_DATE);
        
        final String[] monthNames = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь",
                "октябрь", "ноябрь", "декабрь"};
        StringBuilder sb = new StringBuilder();
        boolean[] monthHasPayments = new boolean[12];
        
        for (byte i = 0; i < 12; i++)
            monthHasPayments[i] = false;
        
        for (Payment payment: financeReport.getPayments())
            if (payment.getYear() == year)
                monthHasPayments[payment.getMonth() - 1] = true;
        
        for (byte i = 0; i < 12; i++)
            if (!monthHasPayments[i]) {
                sb.append(monthNames[i]);
                sb.append(", ");
            }
        
        if (sb.length() > 0)
            sb.delete(sb.length() - 2, sb.length());
        
        return sb.toString();
    }
}
