package es.plaza.retobici.stop;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.bike.BikeDto;
import es.plaza.retobici.spot.Spot;
import es.plaza.retobici.spot.SpotDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/stops")
public class StopController {

    private final StopService stopService;

    private final ModelMapper modelMapper;

    @Autowired
    public StopController(StopService stopService, ModelMapper modelMapper) {
        this.stopService = stopService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Stop> getStops(){ return stopService.getStops(); }

    @PostMapping(path = "/unlock/{stopId}")
    public ResponseEntity<BikeDto> unlockBike(
            @PathVariable("stopId") Long stopId,
            @RequestParam String bikeType
    ){
        Bike bike = stopService.unlockBike(stopId, bikeType);
        BikeDto response = modelMapper.map(bike, BikeDto.class);
        return ResponseEntity.ok(response);

    }

    @PostMapping(path = "/lock/{stopId}")
    public ResponseEntity<SpotDto> lockBike(
            @PathVariable("stopId") Long stopId,
            @RequestParam Long spotId,
            @RequestParam Long bikeId
    ){
        Spot spot = stopService.lockBike(stopId, spotId, bikeId);
        SpotDto response = modelMapper.map(spot, SpotDto.class);
        return ResponseEntity.ok(response);
    }
}
