package es.plaza.retobici.stop;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.bike.BikeService;
import es.plaza.retobici.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopService {

    private final StopRepository stopRepository;

    private final BikeService bikeService;

    @Autowired
    public StopService(StopRepository stopRepository, BikeService bikeService) {
        this.stopRepository = stopRepository;
        this.bikeService = bikeService;
    }

    public List<Stop> getStops(){ return stopRepository.findAll(); }

    public Stop getStop(Long stopId){
        Optional<Stop> stopById = stopRepository.findById(stopId);
        if (stopById.isEmpty()) throw new ApiRequestException("No Stop by given Id");
        return stopById.get();
    }
    public Bike unlockBike(Long stopId, String bikeType) {
        Class<Bike> bikeT = BikeService.parseBikeType(bikeType);
        if (!checkBikeTypeAvailability(stopId,bikeT)) throw new ApiRequestException("No bikes available for that type");
        return getBikeFromStop(stopId, bikeT);
    }

    public boolean checkBikeTypeAvailability(Long stopId, Class<Bike> bikeT) {
        Stop stop = stopRepository.findById(stopId).orElseThrow(() -> new ApiRequestException("No bikes available for that type"));
        List<Bike> bikes = stop.getBikes();
        for (Bike bike: bikes) {

        }
        return true;
    }

    public Bike getBikeFromStop(Long stopId, Class<Bike> bikeT){
        Stop s = stopRepository.findById(stopId).get();
        return bikeService.getBikeFromStop(s, bikeT);
    }

}
