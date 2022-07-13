package kg.megacom.hotel_booking.services.impl;

import kg.megacom.hotel_booking.dao.ImageDao;
import kg.megacom.hotel_booking.mappers.ImageMapper;
import kg.megacom.hotel_booking.models.dtos.ImageDto;
import kg.megacom.hotel_booking.models.entities.Image;
import kg.megacom.hotel_booking.models.response.Message;
import kg.megacom.hotel_booking.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;
    private final ImageMapper imageMapper = ImageMapper.INSTANCE;

    Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public ImageDto save(ImageDto imageDto) {
        Image image = imageMapper.imageDtoToImage(imageDto);
        Image savedImage = imageDao.save(image);
        if (savedImage ==null) log.error("Image not saved: -> " + imageDto);
        log.info("Photo successfully saved");
        return imageMapper.imageToImageDto(image);
    }

    @Override
    public ResponseEntity<?> saveImage(ImageDto imageDto) {
        Image image = imageMapper.imageDtoToImage(imageDto);
        Image savedImage = imageDao.save(image);
        return new ResponseEntity<>(savedImage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateImage(ImageDto imageDto) {
        boolean isExists =  imageDao.existsById(imageDto.getId());
        if(!isExists){
            log.error("Photo not found from database: " + imageDto);
            return  new ResponseEntity<>(Message.of("Photo not found"),HttpStatus.NOT_FOUND);
        }else {
            Image image = imageMapper.imageDtoToImage(imageDto);
            Image updateImage = imageDao.save(image);
            if(updateImage == null) log.error("Failed while updating  image: " + image);
            log.info("Photo successfully updated");
            return  new ResponseEntity<>(updateImage,HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> deleteImage(Long imageId) {
        imageDao.deleteById(imageId);
        log.info("Image successfully deleted: " + imageId);
        return new ResponseEntity<>(Message.of("Image deleted"),HttpStatus.OK);
    }
}
