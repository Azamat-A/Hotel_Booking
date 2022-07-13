package kg.megacom.hotel_booking.models.response;

import lombok.Data;

import java.util.List;

@Data
public class HotelFilterResponse {
   private Long Id;
   private String name;
   private String description;
   private String address;
   private String phone;
   private Double currentScore;
   private String email;
   private List<RoomFilterResponse> availableRooms;
}
