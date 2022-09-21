package es.plaza.retobici.user.rider;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RiderDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
}
