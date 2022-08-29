package es.plaza.retobici.reward;

import lombok.Data;

@Data
public class RewardDto {
    private Long id;
    private String name;
    private String description;
    private Integer points;
    private Integer totalAvailable;
}
