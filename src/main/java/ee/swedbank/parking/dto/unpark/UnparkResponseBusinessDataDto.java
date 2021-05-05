package ee.swedbank.parking.dto.unpark;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Dto of unparking response business data
 */
public class UnparkResponseBusinessDataDto {

    @ApiModelProperty(value = "Amount of money which should be paid for parking")
    private BigDecimal totalPrise;

    public UnparkResponseBusinessDataDto() {
    }

    public UnparkResponseBusinessDataDto(BigDecimal totalPrise) {
        this.totalPrise = totalPrise;
    }

    public BigDecimal getTotalPrise() {
        return totalPrise;
    }

    public void setTotalPrise(BigDecimal totalPrise) {
        this.totalPrise = totalPrise;
    }

    @Override
    public String toString() {
        return "UnparkResponseBusinessDataDto{" +
                "totalPrise=" + totalPrise +
                '}';
    }
}