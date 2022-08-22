package es.plaza.retobici.stop;

import lombok.Data;

import javax.persistence.Column;

@Data
public class StopDto {
    private Long id;
    private Double lng;
    private Double lat;
    private String address;
    private Integer totalSpaces;
}
