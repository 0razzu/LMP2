package finance;


public class FinanceException extends Exception {
    private FinanceErrorCode errorCode;


    public FinanceException(FinanceErrorCode errorCode) {
        this.errorCode = errorCode;
    }


    public FinanceErrorCode getErrorCode() {
        return errorCode;
    }
}