package kg.megacom.hotel_booking.exceptions;

public class CancelBookingErrorException extends RuntimeException {
    public CancelBookingErrorException(String message){
        super(message);
    }
}
