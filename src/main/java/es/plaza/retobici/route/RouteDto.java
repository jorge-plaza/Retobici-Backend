package es.plaza.retobici.route;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.bike.BikeDto;
import es.plaza.retobici.stop.Stop;
import es.plaza.retobici.stop.StopDto;
import es.plaza.retobici.user.Rider;
import es.plaza.retobici.user.RiderDto;
import lombok.Data;

import javax.persistence.Column;

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
}
