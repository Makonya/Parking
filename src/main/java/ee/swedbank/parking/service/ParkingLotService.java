package ee.swedbank.parking.service;

import ee.swedbank.parking.entity.ParkingLot;
import ee.swedbank.parking.exception.ProcessingException;
import ee.swedbank.parking.repository.ParkingLotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static ee.swedbank.parking.enums.ProcessingStatus.ERROR_NO_AVAILABLE_PARKING_LOTS;

/**
 * Service for managing parking lot
 */
@Service
public class ParkingLotService {

    private final Logger logger = LoggerFactory.getLogger(ParkingLotService.class);

    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    /**
     * define the most appropriate parking lot by car characteristics
     *
     * @param carHeight car's height
     * @param carWeight car's weight
     * @return defined parking lot
     * @throws ProcessingException if suitable parking lot was not found
     */
    @Transactional(readOnly = true)
    public ParkingLot defineParkingLot(BigDecimal carHeight, BigDecimal carWeight) throws ProcessingException {
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findAvailableByWeightAndHeight(carHeight, carWeight);
        if (parkingLotOptional.isEmpty()) {
            throw new ProcessingException(ERROR_NO_AVAILABLE_PARKING_LOTS);
        }
        return parkingLotOptional.get();
    }

    /**
     * Book parking lot bu changing availability
     *
     * @param parkingLot parking lot entity
     */
    @Transactional
    public void bookParkingLot(ParkingLot parkingLot) {
        logger.info("Parking lot booking process started {}", parkingLot);
        parkingLot.setAvailable(false);
        parkingLotRepository.save(parkingLot);
    }

    /**
     * Free up parking lot by changing availability
     *
     * @param parkingLot parking lot entity
     */
    @Transactional
    public void freeParkingLot(ParkingLot parkingLot) {
        logger.info("Parking lot free up process started {}", parkingLot);
        parkingLot.setAvailable(true);
        parkingLotRepository.save(parkingLot);
    }
}