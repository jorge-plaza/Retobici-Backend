package es.plaza.retobici.stop;

import es.plaza.retobici.reservation.ReservationService;
import es.plaza.retobici.route.Route;
import es.plaza.retobici.route.RouteDto;
import es.plaza.retobici.spot.Spot;
import es.plaza.retobici.spot.SpotDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/stops")
public class StopController {

    private final StopService stopService;

    private final ModelMapper modelMapper;

    private final ReservationService reservationService;

    @Autowired
    public StopController(StopService stopService, ModelMapper modelMapper, ReservationService reservationService) {
        this.stopService = stopService;
        this.modelMapper = modelMapper;
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Stop> getStops(){ return stopService.getStops(); }

    @PostMapping(path = "/unlock/{stopId}")
    public ResponseEntity<RouteDto> unlockBike(
            @PathVariable("stopId") Stop stop,
            @RequestParam String bikeType,
            Authentication auth
    ){
        Route route = stopService.unlockBike(stop, auth.getName(), bikeType);
        reservationService.disableReservation(auth.getName());
        RouteDto response = modelMapper.map(route, RouteDto.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/lock/{spotId}")
    public ResponseEntity<RouteDto> lockBike(
            @PathVariable Long spotId,
            Authentication auth
    ){
        Route route = stopService.lockBike(spotId, auth.getName());
        RouteDto response = modelMapper.map(route, RouteDto.class);
        return ResponseEntity.ok(response);
    }
}
