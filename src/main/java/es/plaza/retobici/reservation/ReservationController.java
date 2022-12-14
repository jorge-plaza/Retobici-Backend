package es.plaza.retobici.reservation;

import es.plaza.retobici.stop.Stop;
import es.plaza.retobici.user.rider.Rider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<List<ReservationDto>> getReservations(){
        List<ReservationDto> response = reservationService.getAllReservations()
                .stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "{riderId}")
    public List<Reservation> getRiderReservations(@PathVariable("riderId") Rider rider){
        return reservationService.getRiderReservations(rider);
    }

    @PostMapping(path = "{stopId}")
    public ResponseEntity<ReservationDto> reserveBike(
            @PathVariable("stopId") Stop stop,
            @RequestParam String bikeType,
            Authentication auth
    ){
        Reservation reservation = reservationService.reserveBike(auth.getName(), stop, bikeType);
        ReservationDto response = modelMapper.map(reservation, ReservationDto.class);
        return ResponseEntity.ok(response);
    }
}
