package es.plaza.retobici.spot;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class SpotDto {
    private Long id;
    private Long stopId;
    private @Nullable Long bikeId;
}
