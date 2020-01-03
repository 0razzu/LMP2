package finances;


public class Calendar {
    public static boolean wrongDate(int day, int month, int year) {
        return (year <= 0) || (month <= 0) || (month > 12) || (day <= 0) || ((day > 31) ||
                (day > 30) &&
                        ((month == 4) || (month == 6) || (month == 9) || (month == 11)) ||
                (month == 2) &&
                        ((day > 29) ||
                                ((day > 28) && !((year % 400 == 0) || (year % 100 != 0) && (year % 4 == 0)))));
    }
}
