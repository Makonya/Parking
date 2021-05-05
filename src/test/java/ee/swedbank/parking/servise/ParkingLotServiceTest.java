package ee.swedbank.parking.servise;

import ee.swedbank.parking.entity.ParkingLot;
import ee.swedbank.parking.repository.ParkingLotRepository;
import ee.swedbank.parking.service.ParkingLotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParkingLotServiceTest {

    @InjectMocks
    private  ParkingLotService parkingLotService;

    @Mock
    private ParkingLotRepository parkingLotRepository;

    @BeforeEach
    public void setUp(){
        when(parkingLotRepository.save(any())).thenReturn(any());
    }

    @Test
    public void testBookParkingLot() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setNominalWeight(new BigDecimal("3.6"));
        parkingLot.setNominalHeight(new BigDecimal("2.9"));
        parkingLot.setFloorNumber(5);
        parkingLot.setParkingLocation(UUID.randomUUID().toString());
        parkingLot.setPrisePerMinute(new BigDecimal("2.3"));
        parkingLot.setAvailable(true);

        parkingLotService.bookParkingLot(parkingLot);

        assertEquals(parkingLot.getAvailable(), false);
    }
}
