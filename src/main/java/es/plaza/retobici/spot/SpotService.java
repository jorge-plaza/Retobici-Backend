package es.plaza.retobici.spot;

import es.plaza.retobici.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpotService {

    private final SpotRepository spotRepository;

    @Autowired
    public SpotService(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    public Spot findById(SpotId spot_id) {
        return spotRepository.findById(spot_id).orElseThrow(() -> new ApiRequestException("No Spot for that ID"));
    }
}
