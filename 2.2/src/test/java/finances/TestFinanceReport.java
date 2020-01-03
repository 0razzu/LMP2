package finances;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestFinanceReport {
    @Test
    void testFinanceReport1() throws FinanceException {
        Payment[] payments = new Payment[4];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        payments[2] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        payments[3] = new Payment("John Smith", 20, 12, 2012, 199999999);
        
        FinanceReport report = new FinanceReport(payments, "Anonymous user", 10, 8, 2018);
        
        assertAll(
                () -> assertEquals(new Payment("Иванов И. И.", 2, 2, 2002, 1999999), report.getPayments()[0]),
                () -> assertEquals(new Payment("Иванов И. И.", 3, 3, 2003, 91), report.getPayments()[1]),
                () -> assertEquals(new Payment("Петров П. П.", 3, 3, 2003, 2999909), report.getPayment(2)),
                () -> assertEquals(new Payment("John Smith", 20, 12, 2012, 199999999), report.getPayment(3)),
                () -> assertEquals("Anonymous user", report.getReporterName()),
                () -> assertEquals(10, report.getDay()),
                () -> assertEquals(8, report.getMonth()),
                () -> assertEquals(2018, report.getYear())
        );
    }
    
    
    @Test
    void testFinanceReport2() throws FinanceException {
        Payment[] payments = new Payment[2];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        
        FinanceReport report1 = new FinanceReport(payments, "Anonymous user", 29, 2, 2020);
        FinanceReport report2 = new FinanceReport(report1);
        
        assertAll(
                () -> assertEquals(new Payment("Иванов И. И.", 2, 2, 2002, 1999999), report2.getPayments()[0]),
                () -> assertEquals(new Payment("Иванов И. И.", 3, 3, 2003, 91), report2.getPayment(1)),
                () -> assertEquals("Anonymous user", report2.getReporterName()),
                () -> assertEquals(29, report2.getDay()),
                () -> assertEquals(2, report2.getMonth()),
                () -> assertEquals(2020, report2.getYear()),
                
                () -> assertFalse(report1 == report2),
                () -> assertFalse(report1.getPayments() == report2.getPayments())
        );
    }
    
    
    @Test
    void testFinanceReportSetPayments() throws FinanceException {
        Payment[] payments1 = new Payment[2];
        Payment[] payments2 = new Payment[2];
        Payment[] payments3 = new Payment[3];
        
        payments1[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments1[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        payments2[0] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        payments2[1] = new Payment("John Smith", 20, 12, 2012, 199999999);
        payments3[0] = payments1[0];
        
        FinanceReport report = new FinanceReport(payments1, "Anonymous user", 10, 8, 2018);
        report.setPayments(payments2);
        
        assertAll(
                () -> assertEquals(payments2[0], report.getPayments()[0]),
                () -> assertEquals(payments2[1], report.getPayment(1)),
                () -> assertFalse(payments2 == report.getPayments()),
                () -> assertThrows(FinanceException.class, () -> report.setPayments(null)),
                () -> assertThrows(FinanceException.class, () -> report.setPayments(payments3))
        );
    }
    
    
    @Test
    void testFinanceReportSetReporterName() throws FinanceException {
        Payment[] payments = new Payment[2];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments[1] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        
        FinanceReport report = new FinanceReport(payments, "Anonymous user", 10, 8, 2018);
        
        report.setReporterName("Reporter123");
        
        assertAll(
                () -> assertEquals("Reporter123", report.getReporterName()),
                () -> assertThrows(FinanceException.class, () -> report.setReporterName(null)),
                () -> assertThrows(FinanceException.class, () -> report.setReporterName(""))
        );
    }
    
    
    @Test
    void testFinanceReportSetDate() throws FinanceException {
        Payment[] payments = new Payment[2];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments[1] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        
        FinanceReport report = new FinanceReport(payments, "Anonymous user", 10, 8, 2018);
        
        report.setDate(2, 5, 2019);
        
        assertAll(
                () -> assertEquals(2, report.getDay()),
                () -> assertEquals(5, report.getMonth()),
                () -> assertEquals(2019, report.getYear()),
                () -> assertThrows(FinanceException.class, () -> report.setDate(0, 1, 2001)),
                () -> assertThrows(FinanceException.class, () -> report.setDate(1, 0, 2001)),
                () -> assertThrows(FinanceException.class, () -> report.setDate(1, 1, 0)),
                () -> assertThrows(FinanceException.class, () -> report.setDate(31, 4, 2019)),
                () -> assertThrows(FinanceException.class, () -> report.setDate(29, 2, 2001)),
                () -> assertThrows(FinanceException.class, () -> report.setDate(29, 2, 1900))
        );
    }
    
    
    @Test
    void testFinanceReporterToString() throws FinanceException {
        Payment[] payments = new Payment[4];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        payments[2] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        payments[3] = new Payment("John Smith", 20, 12, 2012, 199999999);
        
        FinanceReport report = new FinanceReport(payments, "Anonymous user", 10, 8, 2018);
        
        assertEquals("Автор: Anonymous user, дата: 10.08.2018, платежи:\n" +
                        "    Плательщик: Иванов И. И., дата: 02.02.2002, сумма: 19999 ₽ 99 коп.,\n" +
                        "    Плательщик: Иванов И. И., дата: 03.03.2003, сумма: 0 ₽ 91 коп.,\n" +
                        "    Плательщик: Петров П. П., дата: 03.03.2003, сумма: 29999 ₽ 09 коп.,\n" +
                        "    Плательщик: John Smith, дата: 20.12.2012, сумма: 1999999 ₽ 99 коп.",
                report.toString());
    }
    
    
    @Test
    void testFinanceReportEquals() throws FinanceException {
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
        
        FinanceReport report1 = new FinanceReport(payments1, "Anonymous user", 10, 8, 2018);
        FinanceReport report2 = new FinanceReport(payments1, "Anonymous user", 10, 8, 2018);
        FinanceReport report3 = new FinanceReport(report1);
        FinanceReport report4 = new FinanceReport(payments2, "Anonymous user", 10, 8, 2018);
        FinanceReport report5 = new FinanceReport(payments1, "Составитель отчёта", 10, 8, 2018);
        FinanceReport report6 = new FinanceReport(payments1, "Anonymous user", 11, 8, 2018);
        FinanceReport report7 = new FinanceReport(payments1, "Anonymous user", 10, 9, 2018);
        FinanceReport report8 = new FinanceReport(payments1, "Anonymous user", 10, 8, 2019);
        
        assertAll(
                () -> assertEquals(report1, report2),
                () -> assertEquals(report1, report3),
                () -> assertNotEquals(report1, report4),
                () -> assertNotEquals(report1, report5),
                () -> assertNotEquals(report1, report6),
                () -> assertNotEquals(report1, report7),
                () -> assertNotEquals(report1, report8)
        );
    }
}
