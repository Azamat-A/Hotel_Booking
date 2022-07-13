package kg.megacom.hotel_booking.models.response;

import kg.megacom.hotel_booking.models.enums.EBedType;
import kg.megacom.hotel_booking.models.enums.ETypeOfView;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RoomFilterResponse {
    private Long id;
    private int capacity;
    EBedType bedType;
    float square;
    boolean wifi;
    ETypeOfView typeOfView;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    float totalSum;
}
