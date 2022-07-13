package kg.megacom.hotel_booking.services.impl;

import kg.megacom.hotel_booking.dao.PriceDao;
import kg.megacom.hotel_booking.dao.RoomCategoryDao;
import kg.megacom.hotel_booking.mappers.PriceMapper;
import kg.megacom.hotel_booking.mappers.RoomCategoryMapper;
import kg.megacom.hotel_booking.models.dtos.PriceDto;
import kg.megacom.hotel_booking.models.dtos.RoomCategoryDto;
import kg.megacom.hotel_booking.models.entities.RoomCategory;
import kg.megacom.hotel_booking.models.request.SaveCategoryAndPrice;
import kg.megacom.hotel_booking.services.PriceService;
import kg.megacom.hotel_booking.services.RoomCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class RoomCategoryServiceImpl implements RoomCategoryService {

Logger log  = LoggerFactory.getLogger(RoomCategoryServiceImpl.class);

@Autowired
private RoomCategoryDao roomCategoryDao;
private final RoomCategoryMapper  roomCategoryMapper = RoomCategoryMapper.INSTANCE;
private final PriceMapper priceMapper = PriceMapper.INSTANCE;
@Autowired
private PriceService priceService;


    @Override
    public RoomCategoryDto save(RoomCategoryDto roomCategoryDto) {
        RoomCategory roomCategory = roomCategoryMapper.roomCategoryDtoToRoomCategory(roomCategoryDto);
        RoomCategory savedRoomCategory = roomCategoryDao.save(roomCategory);
        if(savedRoomCategory == null){
            log.error("Failed while saving category: -> " + savedRoomCategory);
        return  null;
        }
        log.info("RoomCategory successfully saved: -> " + savedRoomCategory);
        return roomCategoryMapper.roomCategoryToRoomCategoryDto(roomCategory);
    }



    @Override
    public ResponseEntity<?> saveCategoryAndPrice(SaveCategoryAndPrice saveCategoryAndPrice) {
        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setTypeOfRoom(saveCategoryAndPrice.getTypeOfRoom());
        RoomCategoryDto savedRoomCategory = save(roomCategoryMapper.roomCategoryToRoomCategoryDto(roomCategory));

        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(saveCategoryAndPrice.getPrice());
        priceDto.setStartDate(saveCategoryAndPrice.getStartDate());
        priceDto.setEndDate(saveCategoryAndPrice.getEndDate());
        priceDto.setRoomCategory(savedRoomCategory);
        PriceDto savedPrice = priceService.save(priceDto);

        log.info("SaveCategoryAndPrice successfully saved: " + roomCategory +" price: " + priceDto);
        return  new ResponseEntity<>(savedPrice, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(RoomCategoryDto roomCategoryDto) {

        return null;
    }

    @Override
    public ResponseEntity<?> delete(RoomCategoryDto roomCategoryDto) {
        return null;
    }

    @Override
    public RoomCategoryDto findById(Long roomCategoryId) {
        RoomCategory roomCategory = roomCategoryDao.findById(roomCategoryId).orElse(null);
        return roomCategoryMapper.roomCategoryToRoomCategoryDto(roomCategory);
    }
}
