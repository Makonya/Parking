package ee.swedbank.parking.exception;

import ee.swedbank.parking.enums.ProcessingStatus;

/**
 * Checked exception, occurred while processing parking data
 */
public class ProcessingException extends Exception {

    private static final long serialVersionUID = 1L;

    private final ProcessingStatus errorCode;

    public ProcessingException(ProcessingStatus errorCode) {
        super(errorCode.getMassage());
        this.errorCode = errorCode;
    }

    public ProcessingStatus getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorCode.getMassage();
    }
}