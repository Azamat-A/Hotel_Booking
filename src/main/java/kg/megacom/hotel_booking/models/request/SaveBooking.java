package kg.megacom.hotel_booking.models.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveBooking {

    @NotBlank(message = "Hotel must not be empty") Long hotelId;

    @NotBlank(message = "Room must not be empty") Long roomId;

    @NotBlank(message = "Guest must not be empty") Long guestId;

    @NotBlank(message = "CheckInDate must not be empty") LocalDate checkInDate;

    @NotBlank(message = "CheckOutDate must not be empty") LocalDate checkOtDate;

    @NotBlank (message = "Comment must not be empty")
    String comment;

    @NotBlank(message = "PriceOfBooking must not be empty")
    float priceOfBook;
}

