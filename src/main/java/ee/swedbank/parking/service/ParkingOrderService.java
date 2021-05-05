package ee.swedbank.parking.service;

import ee.swedbank.parking.dto.park.ParkRequestDto;
import ee.swedbank.parking.dto.park.ParkResponseBusinessDataDto;
import ee.swedbank.parking.dto.park.ParkResponseDto;
import ee.swedbank.parking.dto.unpark.UnparkRequestDto;
import ee.swedbank.parking.dto.unpark.UnparkResponseBusinessDataDto;
import ee.swedbank.parking.dto.unpark.UnparkResponseDto;
import ee.swedbank.parking.entity.ParkingLot;
import ee.swedbank.parking.entity.ParkingOrder;
import ee.swedbank.parking.enums.ProcessingStatus;
import ee.swedbank.parking.exception.ProcessingException;
import ee.swedbank.parking.repository.ParkingOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static ee.swedbank.parking.enums.ProcessingStatus.ERROR_CAR_NOT_PARKED;
import static ee.swedbank.parking.enums.ProcessingStatus.ERROR_CAR_PARKED;

/**
 * Service for managing parking orders
 */
@Service
public class ParkingOrderService {

    private final Logger logger = LoggerFactory.getLogger(ParkingOrderService.class);

    private final ParkingOrderRepository parkingOrderRepository;

    private final ParkingLotService parkingLotService;

    public ParkingOrderService(ParkingOrderRepository parkingOrderRepository, ParkingLotService parkingLotService) {
        this.parkingOrderRepository = parkingOrderRepository;
        this.parkingLotService = parkingLotService;
    }

    /**
     * Process request to park a car
     *
     * @param request request data
     * @return processing result
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ParkResponseDto parkCar(ParkRequestDto request) {
        String carNumber = request.getCarNumber();
        BigDecimal carHeight = request.getHeight();
        BigDecimal carWeight = request.getWeight();

        ParkResponseDto responseDto;

        try {
            Optional<ParkingOrder> parkingOrderOptional = parkingOrderRepository.findByCarNumberAndEndDateIsNull(carNumber);
            if (parkingOrderOptional.isPresent()) {
                throw new ProcessingException(ERROR_CAR_PARKED);
            }

            ParkingLot parkingLot = parkingLotService.defineParkingLot(carHeight, carWeight);
            parkingLotService.bookParkingLot(parkingLot);
            ParkingOrder parkingOrder = saveOrderDetails(carNumber, carHeight, carWeight, parkingLot);
            responseDto = createParkCarResponse(parkingOrder);
        } catch (ProcessingException e) {
            logger.error("Error occurred during processing request to park a car", e);
            responseDto = new ParkResponseDto();
            responseDto.setStatus(e.getErrorCode());
            responseDto.setMessage(e.getErrorMessage());
        }
        return responseDto;
    }

    /**
     * Process request to unpark a car
     *
     * @param request request data
     * @return processing result
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UnparkResponseDto unparkCar(UnparkRequestDto request) {
        String carNumber = request.getCarNumber();

        UnparkResponseDto responseDto;

        try {
            Optional<ParkingOrder> parkingOrderOptional = parkingOrderRepository.findByCarNumberAndEndDateIsNull(carNumber);
            if (parkingOrderOptional.isEmpty()) {
                throw new ProcessingException(ERROR_CAR_NOT_PARKED);
            }

            ParkingOrder parkingOrder = parkingOrderOptional.get();
            closeParkingOrder(parkingOrder);
            parkingLotService.freeParkingLot(parkingOrder.getParkingLot());

            responseDto = createUnparkCarResponse(parkingOrder);
        } catch (ProcessingException e) {
            logger.error("Error occurred during processing request to park a car", e);
            responseDto = new UnparkResponseDto();
            responseDto.setStatus(e.getErrorCode());
            responseDto.setMessage(e.getErrorMessage());
        }
        return responseDto;
    }

    /**
     * Save parking order details while car parking process
     *
     * @param carNumber car's number from request
     * @param carHeight car's height from request
     * @param carWeight car's weight from request
     * @return created parking order entity
     */
    private ParkingOrder saveOrderDetails(String carNumber, BigDecimal carHeight, BigDecimal carWeight, ParkingLot parkingLot) {
        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setCarNumber(carNumber);
        parkingOrder.setHeight(carHeight);
        parkingOrder.setWeight(carWeight);
        parkingOrder.setStartDate(LocalDateTime.now());
        parkingOrder.setParkingLot(parkingLot);

        return parkingOrderRepository.save(parkingOrder);
    }

    /**
     * Create success response message to park a car response
     *
     * @param parkingOrder parking order entity
     * @return created response
     */
    private ParkResponseDto createParkCarResponse(ParkingOrder parkingOrder) {
        ParkResponseDto responseDto = new ParkResponseDto();
        responseDto.setStatus(ProcessingStatus.SUCCESS);
        responseDto.setMessage(ProcessingStatus.SUCCESS.getMassage());

        ParkingLot parkingLot = parkingOrder.getParkingLot();
        ParkResponseBusinessDataDto responseData = new ParkResponseBusinessDataDto();
        responseData.setParkingLocation(parkingLot.getParkingLocation());
        responseData.setPricePerMinute(parkingLot.getPrisePerMinute());
        responseData.setFloorNumber(parkingLot.getFloorNumber());
        responseData.setParkingStartDate(parkingOrder.getStartDate());
        responseDto.setResponseData(responseData);
        return responseDto;
    }

    /**
     * Close active parking order for unpark process
     *
     * @param parkingOrder parking order entity
     */
    private void closeParkingOrder(ParkingOrder parkingOrder) {
        parkingOrder.setEndDate(LocalDateTime.now());
        parkingOrderRepository.save(parkingOrder);
    }

    /**
     * Create success response message to unpark a car response
     *
     * @param parkingOrder parking order entity
     * @return created response
     */
    private UnparkResponseDto createUnparkCarResponse(ParkingOrder parkingOrder) {
        UnparkResponseDto responseDto = new UnparkResponseDto();
        responseDto.setStatus(ProcessingStatus.SUCCESS);
        responseDto.setMessage(ProcessingStatus.SUCCESS.getMassage());

        BigDecimal prisePerMinute = parkingOrder.getParkingLot().getPrisePerMinute();
        BigDecimal totalPrise = calculateParkingPrice(parkingOrder.getStartDate(), parkingOrder.getEndDate(), prisePerMinute);
        responseDto.setResponseData(new UnparkResponseBusinessDataDto(totalPrise));

        return responseDto;
    }

    /**
     * Calculate total prise of parking
     *
     * @param startDate      parking start date
     * @param endDate        parking end date
     * @param prisePerMinute car parking prise for 1 minute
     */
    public BigDecimal calculateParkingPrice(LocalDateTime startDate, LocalDateTime endDate, BigDecimal prisePerMinute) {
        long totalMinutesCount = ChronoUnit.MINUTES.between(endDate, startDate);
        // here might be added logic if difference between start date and and date equal to 0
        // (2 request were at the same minute) return prise for one minute
        return prisePerMinute.multiply(new BigDecimal(totalMinutesCount)).abs();
    }
}