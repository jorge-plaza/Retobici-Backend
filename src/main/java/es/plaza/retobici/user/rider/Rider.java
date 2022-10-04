package es.plaza.retobici.user.rider;

import es.plaza.retobici.exception.ApiRequestException;
import es.plaza.retobici.reservation.Reservation;
import es.plaza.retobici.reward.Reward;
import es.plaza.retobici.route.Route;
import es.plaza.retobici.user.role.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Rider {
    @Id
    @SequenceGenerator(
            name = "rider_sequence",
            sequenceName = "rider_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rider_sequence"
    )
    private Long id;
    private String name;
    private String email;

    private String password;
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "rider")
    private List<Route> routes;

    @OneToMany(mappedBy = "rider")
    private List<Reservation> reservations;

    @ManyToMany
    @JoinTable(
            name = "redeemed_rewards",
            joinColumns = @JoinColumn(name = "rider_id"),
            inverseJoinColumns = @JoinColumn(name = "reward_id"))
    private List<Reward> rewardsRedeemed;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "rider_roles",
            joinColumns = @JoinColumn(name = "rider_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Transient
    private Integer age;

    public Rider() {
    }

    public Rider(Long id, String name, String email, String password, LocalDate dateOfBirth, List<Route> routes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.routes = routes;
    }

    public Rider(String name, String email, LocalDate dateOfBirth, List<Route> routes) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.routes = routes;
    }

    public Rider(String name, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Rider(String name, String email, String password, LocalDate dateOfBirth, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){ return password; }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password){ this.password = password; }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return Period.between(dateOfBirth,LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Rider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                '}';
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reward> getRewardsRedeemed() {
        return rewardsRedeemed;
    }

    public void setRewardsRedeemed(List<Reward> rewardsReedemed) {
        this.rewardsRedeemed = rewardsReedemed;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    //TODO revisar como ordena para obtener la ruta activa
    public Route getActiveRoute() {
        return this.routes.stream().sorted().findFirst().orElseThrow(() -> new ApiRequestException("The Rider does not have any Route"));
    }
}
