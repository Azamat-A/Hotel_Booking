package kg.megacom.hotel_booking.models.request;

import kg.megacom.hotel_booking.models.enums.EBedType;
import kg.megacom.hotel_booking.models.enums.ETypeOfRoom;
import kg.megacom.hotel_booking.models.enums.ETypeOfView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveRoom {
    @NotBlank(message = "Capacity must not be empty")
    int capacity;
    @NotBlank(message = "Bed type must not be empty")
    EBedType bedType;
    @NotBlank(message = "Square must not be empty")
    float square;
    @NotBlank(message = "Wi-Fi must not be empty")
    boolean wifi;
    @NotBlank(message = "Hotel must not be empty")
    Long hotelId;
    @NotBlank(message = "Type of view must not be empty")
    ETypeOfView typeOfView;
    @NotBlank(message = "Type of Room must not be empty")
    ETypeOfRoom typeOfRoom;
    Long roomCategoryId;
}
