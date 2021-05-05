package ee.swedbank.parking.dto.unpark;

import ee.swedbank.parking.dto.BaseResponseDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * Dto for parking a car response
 */
public class UnparkResponseDto extends BaseResponseDto {

    @ApiModelProperty(value = "Business data of response to park a car")
    private UnparkResponseBusinessDataDto responseData;

    public UnparkResponseBusinessDataDto getResponseData() {
        return responseData;
    }

    public void setResponseData(UnparkResponseBusinessDataDto responseData) {
        this.responseData = responseData;
    }

    @Override
    public String toString() {
        return "UnparkResponseDto{" +
                "responseData=" + responseData +
                ", status=" + getStatus() +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}