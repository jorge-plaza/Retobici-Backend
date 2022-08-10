package es.plaza.retobici.stop;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.reservation.Reservation;
import es.plaza.retobici.route.Route;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stops")
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stop_seq")
    @SequenceGenerator(name = "stop_seq")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private Double lng;
    @Column(nullable = false)
    private Double lat;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Integer totalSpaces;

    @OneToMany(mappedBy = "stop")
    private List<Bike> bikes;

    @OneToMany(mappedBy = "initialStop")
    private List<Route> routesWithStart;

    @OneToMany(mappedBy = "finalStop")
    private List<Route> routesWithEnd;

    @OneToMany(mappedBy = "stop")
    private List<Reservation> reservations;

    public Stop() {}

    public Stop(Long id, Double lng, Double lat, String address, Integer totalSpaces) {
        this.id = id;
        this.lng = lng;
        this.lat = lat;
        this.address = address;
        this.totalSpaces = totalSpaces;
    }

    public Stop(Double lng, Double lat, String address, Integer totalSpaces) {
        this.lng = lng;
        this.lat = lat;
        this.address = address;
        this.totalSpaces = totalSpaces;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotalSpaces() {
        return totalSpaces;
    }

    public void setTotalSpaces(Integer totalSpaces) {
        this.totalSpaces = totalSpaces;
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    public List<Route> getRoutesWithStart() {
        return routesWithStart;
    }

    public void setRoutesWithStart(List<Route> routesWithStart) {
        this.routesWithStart = routesWithStart;
    }

    public List<Route> getRoutesWithEnd() {
        return routesWithEnd;
    }

    public void setRoutesWithEnd(List<Route> routesWithEnd) {
        this.routesWithEnd = routesWithEnd;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

}