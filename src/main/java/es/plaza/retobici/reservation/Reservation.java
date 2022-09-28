package es.plaza.retobici.reservation;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.stop.Stop;
import es.plaza.retobici.user.rider.Rider;

import javax.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    @SequenceGenerator(name = "reservation_seq")
    @Column(name = "id", nullable = false)
    private Long id;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "rider_id")
   private Rider rider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stop_id")
    private Stop stop;

    @Column(nullable = false)
    private Class<? extends Bike> bike_type;

    private Boolean isActive;

    public Reservation() {}

    public Reservation(Rider rider, Stop stop, Class<? extends Bike> bike_type) {
        this.rider = rider;
        this.stop = stop;
        this.bike_type = bike_type;
        this.isActive = true;
    }

    public Reservation(Long id, Rider rider, Stop stop, Class<? extends Bike> bike_type) {
        this.id = id;
        this.rider = rider;
        this.stop = stop;
        this.bike_type = bike_type;
        this.isActive = true;
    }

    public Reservation(Long id, Rider rider, Stop stop, Class<? extends Bike> bike_type, Boolean isActive) {
        this.id = id;
        this.rider = rider;
        this.stop = stop;
        this.bike_type = bike_type;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public Class<? extends Bike> getBike_type() {
        return bike_type;
    }

    public void setBike_type(Class<? extends Bike> bike_type) {
        this.bike_type = bike_type;
    }

    public boolean ofBikeType(Class<? extends Bike> bikeT) {
        return this.bike_type.equals(bikeT);
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}