package es.plaza.retobici.stop;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.bike.BikeService;
import es.plaza.retobici.exception.ApiRequestException;
import es.plaza.retobici.reservation.Reservation;
import es.plaza.retobici.route.Route;
import es.plaza.retobici.route.RouteService;
import es.plaza.retobici.spot.Spot;
import es.plaza.retobici.spot.SpotId;
import es.plaza.retobici.spot.SpotService;
import es.plaza.retobici.user.Rider;
import es.plaza.retobici.user.RiderService;
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
    public Route unlockBike(Long stopId, String bikeType) {
        Class<Bike> bikeT = BikeService.parseBikeType(bikeType);
        Stop stop = stopRepository.findById(stopId).orElseThrow(() -> new ApiRequestException("No Stop for that ID"));
        if (!checkBikeTypeAvailability(stop,bikeT)) throw new ApiRequestException("No bikes available for that type");
        Bike bike = getBikeFromStop(stopId, bikeT);
        Rider rider = riderService.getRider(1L);
        return routeService.startRoute(rider, stop, bike);
    }

    @Transactional
    public Spot lockBike(Long stopId, Long spotId, Long bikeId){
        Spot spot = spotService.findById(new SpotId(spotId, stopId));
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

    public Bike getBikeFromStop(Long stopId, Class<Bike> bikeT){
        Stop s = stopRepository.findById(stopId).orElseThrow(() -> new ApiRequestException("No Stop for that ID"));
        return bikeService.getBikeFromStop(s, bikeT);
    }

}
