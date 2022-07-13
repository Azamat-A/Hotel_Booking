package kg.megacom.hotel_booking.models.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CancelBooking {
    @NotBlank(message = "Booking id must not be empty")
    Long bookingId;
    @NotBlank (message = "Comment must not be empty")
    String comment;
    @NotBlank (message = "UserId must not be empty")
    Long userId;
}
