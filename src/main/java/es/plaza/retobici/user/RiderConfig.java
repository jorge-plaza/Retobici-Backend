package es.plaza.retobici.user;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.bike.BikeRepository;
import es.plaza.retobici.bike.ElectricBike;
import es.plaza.retobici.spot.Spot;
import es.plaza.retobici.spot.SpotRepository;
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
            BikeRepository bikeRepository,
            SpotRepository spotRepository
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
            repository.saveAll(List.of(u1, u2));

            Stop s1 = new Stop(45.477945964124864, 9.169332864676747, "Arco de la paz", 10);
            stopRepository.save(s1);

            Spot sp1 = new Spot(1L,s1);
            Spot sp2 = new Spot(2L,s1);
            Spot sp3 = new Spot(3L,s1);

            Bike b1 = new Bike();
            ElectricBike b2 = new ElectricBike(60);
            sp1.setBike(b1);
            sp2.setBike(b2);
            bikeRepository.saveAll(List.of(b1,b2));
            spotRepository.saveAll(List.of(sp1,sp2,sp3));
            Bike nb1 = bikeRepository.findById(1L).get();
        };
    }
}
