package es.plaza.retobici.reservation;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.bike.BikeService;
import es.plaza.retobici.exception.ApiException;
import es.plaza.retobici.exception.ApiRequestException;
import es.plaza.retobici.stop.Stop;
import es.plaza.retobici.stop.StopService;
import es.plaza.retobici.user.Rider;
import es.plaza.retobici.user.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Reservation reserveBike(Long stopId, String bikeType) {
        Class<Bike> bikeT = BikeService.parseBikeType(bikeType);
        if (!stopService.checkBikeTypeAvailability(stopId,bikeT)) throw new ApiRequestException("No bike type with that name");
        //TODO get user id
        Long fakeUserId = 1L;
        Stop stop = stopService.getStop(stopId);
        Rider rider = riderService.getRider(fakeUserId);
        Reservation r = new Reservation(rider,stop, bikeT.getSimpleName());
        return reservationRepository.save(r);
    }

}
