package ee.swedbank.parking.dto.park;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Dto for parking a car request
 */
public class ParkRequestDto {

    @ApiModelProperty(value = "Car's unique number")
    private String carNumber;

    @ApiModelProperty(value = "Car's weight")
    private BigDecimal weight;

    @ApiModelProperty(value = "Car's height")
    private BigDecimal height;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ParkRequestDto{" +
                "carNumber='" + carNumber + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}