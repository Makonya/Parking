package ee.swedbank.parking.dto.unpark;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Dto for unparking a car request
 */
public class UnparkRequestDto {

    @ApiModelProperty(value = "Car's unique number")
    private String carNumber;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public String toString() {
        return "UnparkRequestDto{" +
                "carNumber='" + carNumber + '\'' +
                '}';
    }
}