package es.plaza.retobici.reservation;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.bike.BikeService;
import es.plaza.retobici.exception.ApiRequestException;
import es.plaza.retobici.stop.Stop;
import es.plaza.retobici.stop.StopService;
import es.plaza.retobici.user.rider.Rider;
import es.plaza.retobici.user.rider.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final StopService stopService;

    private final RiderService riderService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, StopService stopService, RiderService riderService) {
        this.reservationRepository = reservationRepository;
        this.stopService = stopService;
        this.riderService = riderService;
    }

    public List<Reservation> getAllReservations(){ return reservationRepository.findAll(); }

    public List<Reservation> getRiderReservations(Long rider){ return reservationRepository.findByRider(rider); }

    public Reservation reserveBike(String email, Long stopId, String bikeType) {
        Class<Bike> bikeT = BikeService.parseBikeType(bikeType);
        Stop stop = stopService.getStop(stopId);
        if (!stopService.checkBikeTypeAvailability(stop,bikeT)) throw new ApiRequestException("No bikes available for that type");
        //TODO Schedule timer
        Rider rider = riderService.getRiderByEmail(email);
        if (activeReservation(rider).isPresent()) throw new ApiRequestException("This Rider already has a reservation");
        Reservation r = new Reservation(rider,stop, bikeT);
        return reservationRepository.save(r);
    }

    public Optional<Reservation> activeReservation(Rider rider){
        return reservationRepository.findByRiderAndIsActiveIsTrue(rider);
    }
}
