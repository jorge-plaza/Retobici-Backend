package es.plaza.retobici.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/riders")
public class RiderController {

    private final RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping
    public List<Rider> getRider(){
        return riderService.getRiders();
    }

    @PostMapping
    public void registerRider(@RequestBody Rider rider){
        riderService.addnewRider(rider);
    }

    @DeleteMapping(path = "{riderId}")
    public void deleteRider(@PathVariable("riderId") Long riderId){
        riderService.deleteRider(riderId);
    }

    @PutMapping(path = "{riderId}")
    public void updateRider(
            @PathVariable("riderId") Long riderId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        riderService.updateRider(riderId,name,email);
    }
}
