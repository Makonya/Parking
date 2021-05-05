package ee.swedbank.parking.entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@Entity
@Table(name = "parking_lot")
public class ParkingLot {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nominal_weight", nullable = false)
    private BigDecimal nominalWeight;

    @NotNull
    @Column(name = "nominal_height", nullable = false)
    private BigDecimal nominalHeight;

    @NotNull
    @Column(name = "floor_number", nullable = false)
    private Integer floorNumber;

    @NotNull
    @Column(name = "parking_location", nullable = false, unique = true)
    private String parkingLocation;

    @NotNull
    @Column(name = "prise_per_minute", nullable = false)
    private BigDecimal prisePerMinute;

    @NotNull
    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getNominalWeight() {
        return nominalWeight;
    }

    public void setNominalWeight(BigDecimal nominalWeight) {
        this.nominalWeight = nominalWeight;
    }

    public BigDecimal getNominalHeight() {
        return nominalHeight;
    }

    public void setNominalHeight(BigDecimal nominalHeight) {
        this.nominalHeight = nominalHeight;
    }

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

    public BigDecimal getPrisePerMinute() {
        return prisePerMinute;
    }

    public void setPrisePerMinute(BigDecimal prisePerMinute) {
        this.prisePerMinute = prisePerMinute;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "id=" + id +
                ", nominalWeight=" + nominalWeight +
                ", nominalHeight=" + nominalHeight +
                ", floorNumber=" + floorNumber +
                ", parkingLocation='" + parkingLocation + '\'' +
                ", prisePerMinute=" + prisePerMinute +
                ", isAvailable=" + isAvailable +
                '}';
    }
}