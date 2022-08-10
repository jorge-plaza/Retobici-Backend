package es.plaza.retobici.stop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/stops")
public class StopController {

    private final StopService stopService;

    @Autowired
    public StopController(StopService stopService) { this.stopService = stopService; }

    @GetMapping
    public List<Stop> getStops(){ return stopService.getStops(); }

    @PostMapping(path = "/{stopId}/unlock/{bikeType}")
    public boolean unlockBike(
            @PathVariable("stopId") Long stopId,
            @PathVariable("bikeType") String bikeType
    ){
        return true;
    }
}
