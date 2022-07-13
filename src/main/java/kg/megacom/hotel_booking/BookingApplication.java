package kg.megacom.hotel_booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients("kg.megacom.hotel_booking.microservices")
@SpringBootApplication
public class BookingApplication {
    public static void main(String[] args) {

        SpringApplication.run(BookingApplication.class,args);

    }
}
