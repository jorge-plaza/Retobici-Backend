package es.plaza.retobici.reservation;

import es.plaza.retobici.exception.ApiRequestException;
import es.plaza.retobici.route.Route;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    private final ModelMapper modelMapper;

    @Autowired
    public ReservationController(ReservationService reservationService, ModelMapper modelMapper) {
        this.reservationService = reservationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Reservation> getReservations(){
        return reservationService.getAllReservations();
    }

    @GetMapping(path = "{routeId}")
    public List<Reservation> getRouteById(@PathVariable Long routeId){
        return reservationService.getRiderReservations(routeId);
    }

    @PostMapping(path = "{stopId}")
    public ResponseEntity<ReservationDto> reserveBike(
            @PathVariable("stopId") Long stopId,
            @RequestParam String bikeType
    ){
        Reservation reservation = reservationService.reserveBike(stopId, bikeType);
        ReservationDto response = modelMapper.map(reservation, ReservationDto.class);
        return ResponseEntity.ok(response);
    }
}
