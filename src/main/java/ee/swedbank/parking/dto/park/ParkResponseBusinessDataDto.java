package ee.swedbank.parking.dto.park;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Dto of park response business data
 */
public class ParkResponseBusinessDataDto {

    @ApiModelProperty(value = "Number of floor where parking lot is located")
    private Integer floorNumber;

    @ApiModelProperty(value = "Location of parking lot, unique name of parking lot")
    private String parkingLocation;

    @ApiModelProperty(value = "Price for one minute of parking a car")
    private BigDecimal pricePerMinute;

    @ApiModelProperty(value = "Start date of parking")
    private LocalDateTime parkingStartDate;

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getParkingLocation() {
        return parkingLocation;
    }

    public void setParkingLocation(String parkingLocation) {
        this.parkingLocation = parkingLocation;
    }

    public BigDecimal getPricePerMinute() {
        return pricePerMinute;
    }

    public void setPricePerMinute(BigDecimal pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }

    public LocalDateTime getParkingStartDate() {
        return parkingStartDate;
    }

    public void setParkingStartDate(LocalDateTime parkingStartDate) {
        this.parkingStartDate = parkingStartDate;
    }

    @Override
    public String toString() {
        return "ParkResponseBusinessDataDto{" +
                "floorNumber=" + floorNumber +
                ", parkingLocation='" + parkingLocation + '\'' +
                ", pricePerMinute=" + pricePerMinute +
                ", parkingStartDate=" + parkingStartDate +
                '}';
    }
}