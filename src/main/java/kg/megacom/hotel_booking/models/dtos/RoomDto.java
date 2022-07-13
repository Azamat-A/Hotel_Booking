package kg.megacom.hotel_booking.models.dtos;

import kg.megacom.hotel_booking.models.enums.EBedType;
import kg.megacom.hotel_booking.models.enums.EStatusBooking;
import kg.megacom.hotel_booking.models.enums.ETypeOfView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDto {
    Long id;
    int capacity;
    EBedType bedType;
    float square;
    boolean wifi;
    HotelDto hotel;
    ETypeOfView typeOfView;
    EStatusBooking statusBooking;

    boolean active;
    RoomCategoryDto roomCategory;
}
