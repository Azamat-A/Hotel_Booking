package kg.megacom.hotel_booking.services;


import kg.megacom.hotel_booking.models.dtos.ImageDto;
import kg.megacom.hotel_booking.models.entities.Image;
import org.springframework.http.ResponseEntity;

public interface ImageService {

   ImageDto save (ImageDto imageDto);

   ResponseEntity<?> saveImage (ImageDto imageDto);

   ResponseEntity<?> updateImage (ImageDto imageDto);

   ResponseEntity<?> deleteImage(Long imageId);
}
