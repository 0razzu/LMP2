import finance.*;
import org.junit.jupiter.api.Test;


public class TestFinanceReport {
    @Test
    public void testFinanceReport() throws FinanceException {
        Payment[] payments = new Payment[4];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        payments[2] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        payments[3] = new Payment("Кто-то", 20, 12, 2012, 199999999);
        
        FinanceReport financeReport = new FinanceReport(payments, "Secret user", 10, 8, 2018);
        System.out.println(financeReport);
    
        System.out.println(FinanceReportProcessor.getSumOn("03.03.2003", financeReport));
        System.out.println(FinanceReportProcessor.getMonthsWithoutPayments(financeReport, 2002));
    }
}
