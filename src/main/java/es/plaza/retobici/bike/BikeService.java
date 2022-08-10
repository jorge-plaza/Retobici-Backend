package es.plaza.retobici.bike;

import es.plaza.retobici.exception.ApiRequestException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public static Class<Bike> parseBikeType(String bikeType){
        Class<?> bikeT;
        try{
            bikeT = Class.forName("es.plaza.retobici.bike."+bikeType);
            return (Class<Bike>) bikeT;
        }catch (NoClassDefFoundError | ClassNotFoundException exception){
            throw new ApiRequestException("Wrong bike type");
        }
    }
}
