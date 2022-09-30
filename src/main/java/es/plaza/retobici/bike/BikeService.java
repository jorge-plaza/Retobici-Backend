package es.plaza.retobici.bike;

import es.plaza.retobici.exception.ApiRequestException;
import es.plaza.retobici.spot.Spot;
import es.plaza.retobici.stop.Stop;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    @Autowired
    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<Bike> getAllBikes(){
        return bikeRepository.findAll();
    }

    public Bike getBikeFromStop(Stop stop, Class<Bike> bikeT){
        Bike bike = bikeRepository.findOne(BikeSpecification.searchByType(bikeT,stop))
                .orElseThrow(() -> new ApiRequestException("The is no bike available for that type"));
        Spot spot = bike.getSpot();
        spot.setBike(null);
        return bike;
    }

    @SuppressWarnings("unchecked")
    public static Class<Bike> parseBikeType(String bikeType){
        Class<?> bikeT;
        try{
            bikeT = Class.forName("es.plaza.retobici.bike."+bikeType);
            return (Class<Bike>) bikeT;
        }catch (NoClassDefFoundError | ClassNotFoundException exception){
            throw new ApiRequestException("Wrong bike type");
        }
    }

    public Bike findBikeById(Long bike_id) {
        return bikeRepository.findById(bike_id).orElseThrow(() -> new ApiRequestException("No Bike for that ID"));
    }
}
