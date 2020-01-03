public enum StringErrorCode {
    NEGATIVE_COEFFICIENT("Coefficient cannot be negative"),
    NULL_STRING("String cannot be null"),
    EMPTY_STRING("String cannot be empty");


    private String errorString;


    private StringErrorCode(String errorString) {
        this.errorString = errorString;
    }


    private String getErrorString() {
        return errorString;
    }
}