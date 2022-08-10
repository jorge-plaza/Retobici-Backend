package es.plaza.retobici.reservation;

import lombok.Data;

@Data
public class ReservationDto {
    private Long riderId;
    private Long stopId;
    private String bike_type;
}
