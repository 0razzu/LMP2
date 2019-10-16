package finance;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestPayment {
    @Test
    public void testPayment() throws FinanceException {
        Payment payment1 = new Payment("Иванов И. И.", 1, 1, 2019, 10000);
        Payment payment2 = new Payment("Петров Б. В.", 31, 12, 2018, 19);
        Payment payment3 = new Payment("Anonymous", 29, 2, 2020, 135450);
        
        assertAll(
                () -> assertEquals("Плательщик: Иванов И. И., дата: 01.01.2019, сумма: 100 ₽ 00 коп.", payment1.toString()),
                
                () -> assertEquals("Петров Б. В.", payment2.getName()),
                () -> assertEquals(31, payment2.getDay()),
                () -> assertEquals(12, payment2.getMonth()),
                () -> assertEquals(2018, payment2.getYear()),
                () -> assertEquals(19, payment2.getAmount()),
        
                () -> assertEquals("Anonymous", payment3.getName()),
                () -> assertEquals(29, payment3.getDay()),
                () -> assertEquals(2, payment3.getMonth()),
                () -> assertEquals(2020, payment3.getYear()),
                () -> assertEquals(135450, payment3.getAmount())
        );
    }
    
    
    @Test
    public void testPaymentExceptions() {
        assertAll(
                () -> assertThrows(FinanceException.class, () -> new Payment("", 1, 1, 2000, 1000)),
                () -> assertThrows(FinanceException.class, () -> new Payment(null, 1, 1, 2000, 1000)),
                () -> assertThrows(FinanceException.class, () -> new Payment("name", 50, 1, 2000, 1000)),
                () -> assertThrows(FinanceException.class, () -> new Payment("name", 31, 4, 2000, 1000)),
                () -> assertThrows(FinanceException.class, () -> new Payment("name", 29, 2, 2001, 1000)),
                () -> assertThrows(FinanceException.class, () -> new Payment("name", 29, 2, 1900, 1000)),
                () -> assertThrows(FinanceException.class, () -> new Payment("name", 1, 1, -1, 1000)),
                () -> assertThrows(FinanceException.class, () -> new Payment("name", 1, 1, 2000, 0))
        );
    }
    
    
    @Test
    public void testPaymentEquals() throws FinanceException {
        Payment payment1 = new Payment("Иванов И. И.", 1, 1, 2019, 10000);
        Payment payment2 = new Payment("Иванов И. И.", 1, 1, 2019, 10000);
        Payment payment3 = new Payment("Петров Б. В.", 1, 1, 2019, 10000);
        Payment payment4 = new Payment("Иванов И. И.", 2, 1, 2019, 10000);
        Payment payment5 = new Payment("Иванов И. И.", 1, 2, 2019, 10000);
        Payment payment6 = new Payment("Иванов И. И.", 1, 1, 2020, 10000);
        Payment payment7 = new Payment("Иванов И. И.", 1, 1, 2020, 10001);
        Payment payment8 = payment1;
        
        assertAll(
                () -> assertEquals(payment1, payment2),
                () -> assertNotEquals(payment1, payment3),
                () -> assertNotEquals(payment1, payment4),
                () -> assertNotEquals(payment1, payment5),
                () -> assertNotEquals(payment1, payment6),
                () -> assertNotEquals(payment1, payment7),
                () -> assertEquals(payment1, payment8),
                () -> assertNotEquals(payment1, null),
                () -> assertNotEquals(payment2, "")
        );
    }
}