package es.plaza.retobici.stop;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopService {

    private final StopRepository stopRepository;

    @Autowired
    public StopService(StopRepository stopRepository) { this.stopRepository = stopRepository; }

    public List<Stop> getStops(){ return stopRepository.findAll(); }

    public Stop getStop(Long stopId){
        Optional<Stop> stopById = stopRepository.findById(stopId);
        if (stopById.isEmpty()) throw new ApiRequestException("No Stop by given Id");
        return stopById.get();
    }

    public boolean checkBikeTypeAvailability(Long stopId, Class<Bike> bikeT) {
        Stop stop = stopRepository.findById(stopId).orElseThrow(() -> new ApiRequestException("No bikes of that type available"));
        List<Bike> bikes = stop.getBikes();
        for (Bike bike: bikes) {

        }
        return true;
    }
}
