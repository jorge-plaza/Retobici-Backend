package es.plaza.retobici.bike;

import es.plaza.retobici.route.Route;
import es.plaza.retobici.stop.Stop;

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

    @ManyToOne
    @JoinColumn(name = "stop_id", nullable = false)
    private Stop stop;

    @OneToMany(mappedBy = "bike")
    private List<Route> routes;

    public Bike() {}

    public Bike(Long id, Stop stop) {
        this.id = id;
        this.stop = stop;
    }

    public Bike(Stop stop) {
        this.stop = stop;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
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
}