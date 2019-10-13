package string_processor;


public class StringException extends Exception {
    private StringErrorCode errorCode;


    public StringException(StringErrorCode errorCode) {
        this.errorCode = errorCode;
    }


    public StringErrorCode getErrorCode() {
        return errorCode;
    }
}