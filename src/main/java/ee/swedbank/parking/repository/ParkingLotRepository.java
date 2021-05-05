package ee.swedbank.parking.repository;

import ee.swedbank.parking.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

    @Query(value = "select * from parking_lot where nominal_height >= ?1 and nominal_weight >= ?2 and is_available = true " +
            "order by nominal_weight, nominal_height limit 1;", nativeQuery = true)
    Optional<ParkingLot> findAvailableByWeightAndHeight(BigDecimal carHeight, BigDecimal carWeight);
}