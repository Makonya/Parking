package ee.swedbank.parking.dto;

import ee.swedbank.parking.enums.ProcessingStatus;
import io.swagger.annotations.ApiModelProperty;

/**
 * Dto describing base response class
 */
public class BaseResponseDto {

    @ApiModelProperty(value = "Response status", required = true)
    private ProcessingStatus status;

    @ApiModelProperty(value = "Error message")
    private String message;

    public ProcessingStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessingStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResponseEntity{" +
                "status=" + status +
                ", errorMsg='" + message + '\'' +
                '}';
    }
}