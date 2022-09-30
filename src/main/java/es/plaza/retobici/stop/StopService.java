package es.plaza.retobici.stop;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.bike.BikeService;
import es.plaza.retobici.exception.ApiRequestException;
import es.plaza.retobici.reservation.Reservation;
import es.plaza.retobici.route.Route;
import es.plaza.retobici.route.RouteService;
import es.plaza.retobici.spot.Spot;
import es.plaza.retobici.spot.SpotService;
import es.plaza.retobici.user.rider.Rider;
import es.plaza.retobici.user.rider.RiderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StopService {

    private final StopRepository stopRepository;

    private final BikeService bikeService;

    private final SpotService spotService;

    private final RouteService routeService;

    //TODO remove this with users implementation
    private final RiderService riderService;

    @Autowired
    public StopService(StopRepository stopRepository, BikeService bikeService, SpotService spotService, RouteService routeService, RiderService riderService) {
        this.stopRepository = stopRepository;
        this.bikeService = bikeService;
        this.spotService = spotService;
        this.routeService = routeService;
        this.riderService = riderService;
    }

    public List<Stop> getStops(){ return stopRepository.findAll(); }

    public Stop getStop(Long stopId){
        Optional<Stop> stopById = stopRepository.findById(stopId);
        if (stopById.isEmpty()) throw new ApiRequestException("No Stop by given Id");
        return stopById.get();
    }
    @Transactional
    public Route unlockBike(Stop stop, String email, String bikeType) {
        Class<Bike> bikeT = BikeService.parseBikeType(bikeType);
        Rider rider = riderService.getRiderByEmail(email);
        if (!checkBikeTypeAvailability(stop,bikeT)){
            if (!matchReservation(stop,rider, bikeT)) throw new ApiRequestException("No bikes available for that type");
        }
        Bike bike = getBikeFromStop(stop, bikeT);
        return routeService.startRoute(rider, stop, bike);
    }

    private boolean matchReservation(Stop stop, Rider rider, Class<? extends Bike> bikeT) {
        return stop.getReservations().stream()
                .filter(reservation -> reservation.getRider().equals(rider))
                .filter(reservation -> reservation.ofBikeType(bikeT))
                .anyMatch(Reservation::getActive);
    }

    @Transactional
    public Spot lockBike(Long spotId, Long bikeId){
        Spot spot = spotService.findById(spotId);
        spotService.validateLockSpot(spot);

        Bike bike = bikeService.findBikeById(bikeId);
        spotService.lockBikeOnStop(spot, bike);
        routeService.finishRoute(1L, spot);
        return spot;
    }

    public boolean checkBikeTypeAvailability(@NotNull Stop stop, Class<Bike> bikeT) {
        List<Reservation> reservationsOfType = stop.getReservations()
                .stream()
                .filter(reservation -> reservation.ofBikeType(bikeT))
                .toList();
        List<Bike> bikesOfType = stop.getBikes()
                .stream()
                .filter(bike -> bike.ofType(bikeT))
                .toList();
        return bikesOfType.size() > reservationsOfType.size();
    }

    public Bike getBikeFromStop(Stop stop, Class<Bike> bikeT){
        return bikeService.getBikeFromStop(stop, bikeT);
    }

}
