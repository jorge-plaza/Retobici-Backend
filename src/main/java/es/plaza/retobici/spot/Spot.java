package es.plaza.retobici.spot;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.stop.Stop;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "spots")
@IdClass(SpotId.class)
public class Spot {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "stop_id")
    private Stop stop;

    @OneToOne(targetEntity = Bike.class)
    @Nullable
    private Bike bike;

    public Spot() {
    }

    public Spot(Long id, Stop stop, @Nullable Bike bike) {
        this.id = id;
        this.stop = stop;
        this.bike = bike;
    }

    public Spot(Long id, Stop stop) {
        this.id = id;
        this.stop = stop;
        this.bike = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    @Nullable
    public Bike getBike() {
        return bike;
    }

    public void setBike(@Nullable Bike bike) {
        this.bike = bike;
    }
}
