package ee.swedbank.parking.web.rest;

import ee.swedbank.parking.dto.park.ParkRequestDto;
import ee.swedbank.parking.dto.park.ParkResponseDto;
import ee.swedbank.parking.dto.unpark.UnparkRequestDto;
import ee.swedbank.parking.dto.unpark.UnparkResponseDto;
import ee.swedbank.parking.service.ParkingOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for autonomous parking lot
 */
@RestController
@RequestMapping("api")
public class ParkingResource {

    private final ParkingOrderService parkingOrderService;

    public ParkingResource(ParkingOrderService parkingOrderService) {
        this.parkingOrderService = parkingOrderService;
    }

    /**
     * {@code POST /park} : Park a car
     *
     * @param request {@link ParkRequestDto} car data
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body {@link ParkResponseDto}
     */
    @PostMapping(path = "park")
    public ResponseEntity<ParkResponseDto> parkCar(@RequestBody ParkRequestDto request) {
        ParkResponseDto resultDto = parkingOrderService.parkCar(request);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    /**
     * {@code POST /unpark} : Unpark a car
     *
     * @param request {@link UnparkRequestDto} car data
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body {@link UnparkResponseDto}
     */
    @PostMapping(path = "unpark")
    public ResponseEntity<UnparkResponseDto> unparkCar(@RequestBody UnparkRequestDto request) {
        UnparkResponseDto resultDto = parkingOrderService.unparkCar(request);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}