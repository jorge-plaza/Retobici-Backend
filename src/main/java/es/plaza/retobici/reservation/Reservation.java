package es.plaza.retobici.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.plaza.retobici.stop.Stop;
import es.plaza.retobici.user.Rider;
import org.springframework.lang.Nullable;

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
    private String bike_type;

    public Reservation() {}

    public Reservation(Rider rider, Stop stop, String bike_type) {
        this.rider = rider;
        this.stop = stop;
        this.bike_type = bike_type;
    }

    public Reservation(Long id, Rider rider, Stop stop, String bike_type) {
        this.id = id;
        this.rider = rider;
        this.stop = stop;
        this.bike_type = bike_type;
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

    public String getBike_type() {
        return bike_type;
    }

    public void setBike_type(String bike_type) {
        this.bike_type = bike_type;
    }

}