package ee.swedbank.parking.dto.park;

import ee.swedbank.parking.dto.BaseResponseDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * Dto for parking a car response
 */
public class ParkResponseDto extends BaseResponseDto {

    @ApiModelProperty(value = "Business data of response to park a car")
    private ParkResponseBusinessDataDto responseData;

    public ParkResponseBusinessDataDto getResponseData() {
        return responseData;
    }

    public void setResponseData(ParkResponseBusinessDataDto responseData) {
        this.responseData = responseData;
    }

    @Override
    public String toString() {
        return "ParkResponseDto{" +
                "responseData=" + responseData +
                ", status=" + getStatus() +
                ", errorMsg='" + getMessage() + '\'' +
                '}';
    }
}