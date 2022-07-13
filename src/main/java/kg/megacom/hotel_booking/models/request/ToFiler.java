package kg.megacom.hotel_booking.models.request;

import kg.megacom.hotel_booking.models.enums.EBedType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ToFiler {

    @NotBlank(message = "City id must not be empty")
    Long cityId;

    @NotBlank(message = "CheckInDate id must not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate checkInDate;

    @NotBlank(message = "CheckOutDate id must not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate checkOutDate;

    @NotBlank(message = "Number of Person id not be empty")
    int numberOfPerson;

    @NotBlank(message = "Bed type must not be empty")
    EBedType bedType;

}
