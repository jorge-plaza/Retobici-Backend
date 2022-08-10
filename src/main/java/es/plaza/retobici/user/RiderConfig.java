package es.plaza.retobici.user;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.bike.BikeRepository;
import es.plaza.retobici.bike.ElectricBike;
import es.plaza.retobici.stop.Stop;
import es.plaza.retobici.stop.StopRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class RiderConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            RiderRepository repository,
            StopRepository stopRepository,
            BikeRepository bikeRepository
    ){
        return args -> {
            Rider u1 = new Rider(
                    "Jorge",
                    "jorge@mail.com",
                    LocalDate.of(2000, Month.DECEMBER,5)
            );
            Rider u2 = new Rider(
                    "Cristina",
                    "cris@mail.com",
                    LocalDate.of(2000, Month.DECEMBER,6)
            );

            Stop s1 = new Stop(45.477945964124864, 9.169332864676747, "Arco de la paz", 10);

            Bike b1 = new Bike(s1);
            ElectricBike b2 = new ElectricBike(s1,60);

            repository.saveAll(List.of(u1, u2));
            stopRepository.save(s1);
            bikeRepository.saveAll(List.of(b1,b2));


        };
    }
}
