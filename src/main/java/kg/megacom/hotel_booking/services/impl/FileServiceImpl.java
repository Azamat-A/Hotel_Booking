package kg.megacom.hotel_booking.services.impl;

import kg.megacom.hotel_booking.microservices.json.FileServiceFeign;
import kg.megacom.hotel_booking.microservices.json.FileServiceResponse;
import kg.megacom.hotel_booking.models.dtos.HotelDto;
import kg.megacom.hotel_booking.models.dtos.ImageDto;
import kg.megacom.hotel_booking.models.response.Message;
import kg.megacom.hotel_booking.services.FileService;
import kg.megacom.hotel_booking.services.HotelService;
import kg.megacom.hotel_booking.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileServiceFeign fileServiceFeign;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private ImageService imageService;

    @Override
    public ResponseEntity<?> uploadImageToHotel(MultipartFile file, Long hotelId, int orderNum) {
       HotelDto hotelDto = hotelService.findById(hotelId);

       if(hotelDto != null){
           ImageDto imageDto = new ImageDto();
           imageDto.setHotel(hotelDto);
           imageDto.setPosition(orderNum);
           try{
               FileServiceResponse response = fileServiceFeign.upload(file);
               imageDto.setLink(response.getDownloadUri());
               ImageDto savedImage = imageService.save(imageDto);

               return new ResponseEntity<>(savedImage,HttpStatus.OK);
           }catch (Exception e){
               System.out.println(e);
               return new ResponseEntity<>(Message.of("Не удалось сохранить фото"), HttpStatus.NOT_ACCEPTABLE);
           }
       }

       return  new ResponseEntity<>(Message.of("Не удалось найти обьект отеля"), HttpStatus.NOT_ACCEPTABLE);
    }
}
