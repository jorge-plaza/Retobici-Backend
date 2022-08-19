package es.plaza.retobici.bike;

import es.plaza.retobici.route.Route;
import es.plaza.retobici.spot.Spot;
import es.plaza.retobici.stop.Stop;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bikes")
@Inheritance(strategy = InheritanceType.JOINED)
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bike_seq")
    @SequenceGenerator(name = "bike_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "bike")
    private List<Route> routes;

    @OneToOne(targetEntity = Spot.class, mappedBy = "bike")
    private Spot spot;

    @Transient
    private Stop stop;

    public Bike() {}

    public Bike(Long id, List<Route> routes, Spot spot, Stop stop) {
        this.id = id;
        this.routes = routes;
        this.spot = spot;
        this.stop = stop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Stop getStop() {
        return this.spot != null ? this.spot.getStop() : null;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", routes=" + routes +
                ", spot=" + spot +
                '}';
    }
}