package ee.swedbank.parking.repository;

import ee.swedbank.parking.entity.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingOrderRepository extends JpaRepository<ParkingOrder, Long> {

    Optional<ParkingOrder> findByCarNumberAndEndDateIsNull(String carNumber);
}