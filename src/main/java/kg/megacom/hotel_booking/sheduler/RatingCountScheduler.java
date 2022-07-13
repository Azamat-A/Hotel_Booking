package kg.megacom.hotel_booking.sheduler;

import kg.megacom.hotel_booking.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RatingCountScheduler {

    @Autowired
    HotelService hotelService;

//    @Scheduled(cron = "0 0 2 * * *")
//    public void scheduleCronTask() {
//        hotelService.countCurrentScore();
//    }
}
