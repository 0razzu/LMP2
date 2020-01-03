package finances;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestFinanceReportProcessor {
    @Test
    void testFinanceReportProcessorGetStartingWith() throws FinanceException {
        Payment[] payments1 = new Payment[4];
        Payment[] payments2 = new Payment[2];
        Payment[] payments3 = new Payment[1];
        
        payments1[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments1[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        payments1[2] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        payments1[3] = new Payment("John Smith", 20, 12, 2012, 199999999);
        
        payments2[0] = payments1[0];
        payments2[1] = payments1[1];
        
        payments3[0] = payments1[3];
        
        FinanceReport report1 = new FinanceReport(payments1, "Anonymous user", 10, 8, 2018);
        FinanceReport report2 = new FinanceReport(payments2, "Anonymous user", 10, 8, 2018);
        FinanceReport report3 = new FinanceReport(payments3, "Anonymous user", 10, 8, 2018);
        FinanceReport report4 = new FinanceReport(new Payment[0], "Anonymous user", 10, 8, 2018);
        
        assertAll(
                () -> assertEquals(report2, FinanceReportProcessor.getStartingWith('И', report1)),
                () -> assertEquals(report3, FinanceReportProcessor.getStartingWith('J', report1)),
                () -> assertEquals(report4, FinanceReportProcessor.getStartingWith('Q', report1)),
                () -> assertThrows(FinanceException.class,
                        () -> FinanceReportProcessor.getStartingWith('Q', null))
        );
    }
    
    
    @Test
    void testFinanceReportProcessorGetPaymentsLessThan() throws FinanceException {
        Payment[] payments1 = new Payment[4];
        Payment[] payments2 = new Payment[2];
        Payment[] payments3 = new Payment[1];
        
        payments1[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments1[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        payments1[2] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        payments1[3] = new Payment("John Smith", 20, 12, 2012, 199999999);
        
        payments2[0] = payments1[0];
        payments2[1] = payments1[1];
        
        payments3[0] = payments1[1];
        
        FinanceReport report1 = new FinanceReport(payments1, "Anonymous user", 10, 8, 2018);
        FinanceReport report2 = new FinanceReport(payments2, "Anonymous user", 10, 8, 2018);
        FinanceReport report3 = new FinanceReport(payments3, "Anonymous user", 10, 8, 2018);
        FinanceReport report4 = new FinanceReport(new Payment[0], "Anonymous user", 10, 8, 2018);
        
        assertAll(
                () -> assertEquals(report1,
                        FinanceReportProcessor.getPaymentsLessThan(200000000, report1)),
                () -> assertEquals(report2,
                        FinanceReportProcessor.getPaymentsLessThan(2000000, report1)),
                () -> assertEquals(report3,
                        FinanceReportProcessor.getPaymentsLessThan(1999999, report1)),
                () -> assertEquals(report4,
                        FinanceReportProcessor.getPaymentsLessThan(10, report1)),
                () -> assertThrows(FinanceException.class,
                        () -> FinanceReportProcessor.getPaymentsLessThan(1000, null))
        );
    }
    
    
    @Test
    void testFinanceReportProcessorGetSumOn() throws FinanceException {
        Payment[] payments = new Payment[4];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2002, 1999999);
        payments[1] = new Payment("Иванов И. И.", 3, 3, 2003, 91);
        payments[2] = new Payment("Петров П. П.", 3, 3, 2003, 2999909);
        payments[3] = new Payment("John Smith", 20, 12, 2012, 199999999);
        
        FinanceReport report1 = new FinanceReport(payments, "Anonymous user", 10, 8, 2018);
        FinanceReport report2 = new FinanceReport(new Payment[0], "Anonymous user", 10, 8, 2018);
        
        assertAll(
                () -> assertEquals(3000000, FinanceReportProcessor.getSumOn("03.03.2003", report1)),
                () -> assertEquals(1999999, FinanceReportProcessor.getSumOn("02.02.2002", report1)),
                () -> assertEquals(0, FinanceReportProcessor.getSumOn("01.10.2010", report1)),
                () -> assertEquals(0, FinanceReportProcessor.getSumOn("02.02.2002", report2)),
                () -> assertThrows(FinanceException.class,
                        () -> FinanceReportProcessor.getSumOn("30.02.2019", report1)),
                () -> assertThrows(FinanceException.class,
                        () -> FinanceReportProcessor.getSumOn("!", report1)),
                () -> assertThrows(FinanceException.class,
                        () -> FinanceReportProcessor.getSumOn(null, report1)),
                () -> assertThrows(FinanceException.class,
                        () -> FinanceReportProcessor.getSumOn("01.10.2010", null))
        );
    }
    
    
    @Test
    void testFinanceReportProcessorGetMonthsWithoutPayments() throws FinanceException {
        Payment[] payments = new Payment[19];
        
        payments[0] = new Payment("Иванов И. И.", 2, 2, 2018, 1999999);
        payments[1] = new Payment("Иванов И. И.", 3, 3, 2018, 91);
        payments[2] = new Payment("Петров П. П.", 3, 3, 2018, 2999909);
        payments[3] = new Payment("John Smith", 20, 12, 2018, 199999999);
        payments[4] = new Payment("Иванов И. И.", 5, 4, 2019, 91);
        payments[5] = new Payment("Петров П. П.", 5, 4, 2019, 2999909);
        payments[6] = new Payment("Иванов И. И.", 2, 11, 2019, 1999999);
        payments[7] = new Payment("Иванов И. И.", 10, 1, 2020, 1999999);
        payments[8] = new Payment("Иванов И. И.", 10, 2, 2020, 1999999);
        payments[9] = new Payment("Иванов И. И.", 10, 3, 2020, 1999999);
        payments[10] = new Payment("Иванов И. И.", 2, 4, 2020, 1999999);
        payments[11] = new Payment("Иванов И. И.", 2, 5, 2020, 1999999);
        payments[12] = new Payment("Иванов И. И.", 2, 6, 2020, 1999999);
        payments[13] = new Payment("Иванов И. И.", 2, 7, 2020, 1999999);
        payments[14] = new Payment("Иванов И. И.", 2, 8, 2020, 1999999);
        payments[15] = new Payment("Иванов И. И.", 2, 9, 2020, 1999999);
        payments[16] = new Payment("Иванов И. И.", 2, 10, 2020, 1999999);
        payments[17] = new Payment("Иванов И. И.", 2, 11, 2020, 1999999);
        payments[18] = new Payment("Иванов И. И.", 2, 12, 2020, 1999999);
        
        FinanceReport report1 = new FinanceReport(payments, "Anonymous user", 10, 8, 2018);
        FinanceReport report2 = new FinanceReport(new Payment[0], "Anonymous user", 10, 8, 2018);
        
        assertAll(
                () -> assertEquals("январь, апрель, май, июнь, июль, август, сентябрь, октябрь, ноябрь",
                        FinanceReportProcessor.getMonthsWithoutPayments(report1, 2018)),
                () -> assertEquals("январь, февраль, март, май, июнь, июль, август, сентябрь, октябрь, декабрь",
                        FinanceReportProcessor.getMonthsWithoutPayments(report1, 2019)),
                () -> assertEquals(
                        "январь, февраль, март, апрель, май, июнь, июль, август, сентябрь, октябрь, ноябрь, декабрь",
                        FinanceReportProcessor.getMonthsWithoutPayments(report1, 2017)),
                () -> assertEquals(
                        "январь, февраль, март, апрель, май, июнь, июль, август, сентябрь, октябрь, ноябрь, декабрь",
                        FinanceReportProcessor.getMonthsWithoutPayments(report2, 2018)),
                () -> assertEquals("", FinanceReportProcessor.getMonthsWithoutPayments(report1, 2020)),
                () -> assertThrows(FinanceException.class,
                        () -> FinanceReportProcessor.getMonthsWithoutPayments(null, 2012)),
                () -> assertThrows(FinanceException.class,
                        () -> FinanceReportProcessor.getMonthsWithoutPayments(report1, -2012))
        );
    }
}
