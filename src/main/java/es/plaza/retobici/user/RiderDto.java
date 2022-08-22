package es.plaza.retobici.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RiderDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
}
