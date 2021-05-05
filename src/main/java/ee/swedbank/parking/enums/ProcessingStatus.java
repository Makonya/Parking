package ee.swedbank.parking.enums;

/**
 * Enum, which store errors
 */
public enum ProcessingStatus {
    SUCCESS("Request successfully processed."),
    ERROR_CAR_PARKED("Car was already parked."),
    ERROR_CAR_NOT_PARKED("No active parking for this car."),
    ERROR_NO_AVAILABLE_PARKING_LOTS("There is no available parking lots.");

    private final String massage;

    ProcessingStatus(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }
}