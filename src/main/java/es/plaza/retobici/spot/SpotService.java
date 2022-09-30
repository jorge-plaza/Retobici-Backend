package es.plaza.retobici.spot;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static es.plaza.retobici.spot.SpotRegisterBikeValidator.*;
import static es.plaza.retobici.spot.SpotRegisterBikeValidator.ValidationResult.SUCCESS;

@Service
public class SpotService {

    private final SpotRepository spotRepository;
    @Autowired
    public SpotService(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    public Spot findById(Long spot_id) {
        return spotRepository.findById(spot_id).orElseThrow(() -> new ApiRequestException("No Spot for that ID"));
    }

    public void lockBikeOnStop(Spot spot, Bike bike) {
        spot.setBike(bike);
    }

    public void validateLockSpot(Spot spot) {
        ValidationResult result = SpotRegisterBikeValidator
                .isEmpty()
                .apply(spot);
        if (result!= SUCCESS) throw new ApiRequestException("Spot unable to lock bike");
    }
}
