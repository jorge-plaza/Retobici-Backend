package es.plaza.retobici.bike;

import es.plaza.retobici.stop.Stop;
import lombok.Data;

@Data
public class BikeDto {
    private Long id;
    private Long stopId;
    private Integer battery;
    private String type;
}
