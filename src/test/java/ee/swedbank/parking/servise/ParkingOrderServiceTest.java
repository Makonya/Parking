package ee.swedbank.parking.servise;

import ee.swedbank.parking.service.ParkingOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ParkingOrderServiceTest {

    @InjectMocks
    ParkingOrderService parkingOrderService;

    @Test
    public void testCalculateParkingPrice() {
        long minuteDifference = 2;
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusMinutes(minuteDifference);
        BigDecimal prisePerMinute = new BigDecimal("2.5");
        BigDecimal totalPrise = parkingOrderService.calculateParkingPrice(startDate, endDate, prisePerMinute);
        assertEquals(totalPrise, prisePerMinute.multiply(new BigDecimal(minuteDifference)).abs());
    }
}