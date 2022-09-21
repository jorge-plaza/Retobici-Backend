package es.plaza.retobici.route;

import es.plaza.retobici.bike.BikeDto;
import es.plaza.retobici.stop.StopDto;
import es.plaza.retobici.user.rider.RiderDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RouteDto {
    private Long id;
    private RiderDto rider;
    private StopDto initialStop;
    private StopDto finalStop;
    private BikeDto bike;
    private Float distance;
    private Integer duration;
    private Integer estimatedDuration;
    private Integer points;
    private LocalDateTime createDateTime;
}
