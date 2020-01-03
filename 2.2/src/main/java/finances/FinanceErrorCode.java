package finances;


public enum FinanceErrorCode {
    WRONG_NAME("Name cannot be null or empty string"),
    WRONG_DATE("Attempt to set non-existent date"),
    WRONG_AMOUNT("Amount of money cannot be 0"),
    NULL_PAYMENTS("Payments cannot be null"),
    NULL_REPORT("Finance report cannot be null");
    
    
    private String errorString;
    
    
    private FinanceErrorCode(String errorString) {
        this.errorString = errorString;
    }
    
    
    public String getErrorString() {
        return errorString;
    }
}