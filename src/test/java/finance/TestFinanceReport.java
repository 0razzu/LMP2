package finance;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestFinanceReport {
    @Test
    public void testFinanceReport1() throws FinanceException {
        Payment[] payments = new Payment[4];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        payments[2] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        payments[3] = new Payment("John Smith", 20, 12, 2012, 199999999);
        
        FinanceReport financeReport = new FinanceReport(payments, "Anonymous user", 10, 8, 2018);
        
        assertAll(
                () -> assertEquals(new Payment("Иванов И. И.", 2, 2, 2002, 1999999), financeReport.getPayments()[0]),
                () -> assertEquals(new Payment("Иванов И. И.", 3, 3, 2003, 91), financeReport.getPayments()[1]),
                () -> assertEquals(new Payment("Петров П. П.", 3, 3, 2003, 2999909), financeReport.getPayment(2)),
                () -> assertEquals(new Payment("John Smith", 20, 12, 2012, 199999999), financeReport.getPayment(3)),
                () -> assertEquals("Anonymous user", financeReport.getReporterName()),
                () -> assertEquals(10, financeReport.getDay()),
                () -> assertEquals(8, financeReport.getMonth()),
                () -> assertEquals(2018, financeReport.getYear())
        );
    }
    
    
    @Test
    public void testFinanceReport2() throws FinanceException {
        Payment[] payments = new Payment[2];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        
        FinanceReport financeReport1 = new FinanceReport(payments, "Anonymous user", 29, 2, 2020);
        FinanceReport financeReport2 = new FinanceReport(financeReport1);
        
        assertAll(
                () -> assertEquals(new Payment("Иванов И. И.", 2, 2, 2002, 1999999), financeReport2.getPayments()[0]),
                () -> assertEquals(new Payment("Иванов И. И.", 3, 3, 2003, 91), financeReport2.getPayment(1)),
                () -> assertEquals("Anonymous user", financeReport2.getReporterName()),
                () -> assertEquals(29, financeReport2.getDay()),
                () -> assertEquals(2, financeReport2.getMonth()),
                () -> assertEquals(2020, financeReport2.getYear()),
                
                () -> assertFalse(financeReport1 == financeReport2),
                () -> assertFalse(financeReport1.getPayments() == financeReport2.getPayments())
        );
    }
    
    
    @Test
    public void testFinanceReportSetPayments() throws FinanceException {
        Payment[] payments1 = new Payment[2];
        Payment[] payments2 = new Payment[2];
        Payment[] payments3 = new Payment[3];
        
        payments1[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments1[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        payments2[0] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        payments2[1] = new Payment("John Smith", 20, 12, 2012, 199999999);
        payments3[0] = payments1[0];
        
        FinanceReport financeReport = new FinanceReport(payments1, "Anonymous user", 10, 8, 2018);
        financeReport.setPayments(payments2);
        
        assertAll(
                () -> assertEquals(payments2[0], financeReport.getPayments()[0]),
                () -> assertEquals(payments2[1], financeReport.getPayment(1)),
                () -> assertFalse(payments2 == financeReport.getPayments()),
                () -> assertThrows(FinanceException.class, () -> financeReport.setPayments(null)),
                () -> assertThrows(FinanceException.class, () -> financeReport.setPayments(payments3))
        );
    }
    
    
    @Test
    public void testFinanceReportSetReporterName() throws FinanceException {
        Payment[] payments = new Payment[2];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments[1] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        
        FinanceReport financeReport = new FinanceReport(payments, "Anonymous user", 10, 8, 2018);
        
        financeReport.setReporterName("Reporter123");
        
        assertAll(
                () -> assertEquals("Reporter123", financeReport.getReporterName()),
                () -> assertThrows(FinanceException.class, () -> financeReport.setReporterName(null)),
                () -> assertThrows(FinanceException.class, () -> financeReport.setReporterName(""))
        );
    }
    
    
    @Test
    public void testFinanceReportSetDate() throws FinanceException {
        Payment[] payments = new Payment[2];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments[1] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        
        FinanceReport financeReport = new FinanceReport(payments, "Anonymous user", 10, 8, 2018);
        
        financeReport.setDate(2, 5, 2019);
        
        assertAll(
                () -> assertEquals(2, financeReport.getDay()),
                () -> assertEquals(5, financeReport.getMonth()),
                () -> assertEquals(2019, financeReport.getYear()),
                () -> assertThrows(FinanceException.class, () -> financeReport.setDate(0, 1, 2001)),
                () -> assertThrows(FinanceException.class, () -> financeReport.setDate(1, 0, 2001)),
                () -> assertThrows(FinanceException.class, () -> financeReport.setDate(1, 1, 0)),
                () -> assertThrows(FinanceException.class, () -> financeReport.setDate(31, 4, 2019)),
                () -> assertThrows(FinanceException.class, () -> financeReport.setDate(29, 2, 2001)),
                () -> assertThrows(FinanceException.class, () -> financeReport.setDate(29, 2, 1900))
        );
    }
    
    
    @Test
    public void testFinanceReporterToString() throws FinanceException {
        Payment[] payments = new Payment[4];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        payments[2] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        payments[3] = new Payment("John Smith", 20, 12, 2012, 199999999);
        
        FinanceReport financeReport = new FinanceReport(payments, "Anonymous user", 10, 8, 2018);
        
        assertEquals("Автор: Anonymous user, дата: 10.08.2018, платежи:\n" +
                        "    Плательщик: Иванов И. И., дата: 02.02.2002, сумма: 19999 ₽ 99 коп.,\n" +
                        "    Плательщик: Иванов И. И., дата: 03.03.2003, сумма: 0 ₽ 91 коп.,\n" +
                        "    Плательщик: Петров П. П., дата: 03.03.2003, сумма: 29999 ₽ 09 коп.,\n" +
                        "    Плательщик: John Smith, дата: 20.12.2012, сумма: 1999999 ₽ 99 коп.",
                financeReport.toString());
    }
    
    
    @Test
    public void testFinanceReportEquals() throws FinanceException {
        Payment[] payments1 = new Payment[4];
        Payment[] payments2 = new Payment[4];
    
        payments1[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments1[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        payments1[2] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        payments1[3] = new Payment("John Smith", 20, 12, 2012, 199999999);
        
        payments2[0] = payments1[0];
        payments2[1] = payments1[1];
        payments2[2] = payments1[3];
        payments2[3] = payments1[2];
    
        FinanceReport financeReport1 = new FinanceReport(payments1, "Anonymous user", 10, 8, 2018);
        FinanceReport financeReport2 = new FinanceReport(payments1, "Anonymous user", 10, 8, 2018);
        FinanceReport financeReport3 = new FinanceReport(financeReport1);
        FinanceReport financeReport4 = new FinanceReport(payments2, "Anonymous user", 10, 8, 2018);
        FinanceReport financeReport5 = new FinanceReport(payments1, "Составитель отчёта", 10, 8, 2018);
        FinanceReport financeReport6 = new FinanceReport(payments1, "Anonymous user", 11, 8, 2018);
        FinanceReport financeReport7 = new FinanceReport(payments1, "Anonymous user", 10, 9, 2018);
        FinanceReport financeReport8 = new FinanceReport(payments1, "Anonymous user", 10, 8, 2019);
        
        assertAll(
                () -> assertEquals(financeReport1, financeReport2),
                () -> assertEquals(financeReport1, financeReport3),
                () -> assertNotEquals(financeReport1, financeReport4),
                () -> assertNotEquals(financeReport1, financeReport5),
                () -> assertNotEquals(financeReport1, financeReport6),
                () -> assertNotEquals(financeReport1, financeReport7),
                () -> assertNotEquals(financeReport1, financeReport8)
        );
    }
}
