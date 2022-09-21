package es.plaza.retobici.route;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.stop.Stop;
import es.plaza.retobici.user.rider.Rider;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_seq")
    @SequenceGenerator(name = "route_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rider_id")
    private Rider rider;

    @ManyToOne
    @JoinColumn(name = "initial_stop_id", nullable = false)
    private Stop initialStop;

    @ManyToOne
    @JoinColumn(name = "final_stop_id", nullable = true)
    private Stop finalStop;

    @ManyToOne
    @JoinColumn(name = "bike_id", nullable = false)
    private Bike bike;

    @Column(name = "distance", nullable = true)
    private Float distance;
    @Column(name = "duration", nullable = true)
    private Integer duration;
    @Column(name = "estimated_duration", nullable = true)
    private Integer estimatedDuration;
    @Column(name = "points", nullable = true)
    private Integer points;
    @Column(name = "mapbox_response", nullable = true)
    private String mapboxResponse;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Route() {}

    public Route(Long id, Rider rider, Stop initialStop, Stop finalStop, Bike bike, Float distance, Integer duration, Integer estimatedDuration, Integer points, String mapboxResponse) {
        this.id = id;
        this.rider = rider;
        this.initialStop = initialStop;
        this.finalStop = finalStop;
        this.bike = bike;
        this.distance = distance;
        this.duration = duration;
        this.estimatedDuration = estimatedDuration;
        this.points = points;
        this.mapboxResponse = mapboxResponse;
    }

    public Route(Rider rider, Stop initialStop, Bike bike) {
        this.rider = rider;
        this.initialStop = initialStop;
        this.bike = bike;
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

    public Stop getInitialStop() {
        return initialStop;
    }

    public void setInitialStop(Stop initialStop) {
        this.initialStop = initialStop;
    }

    public Stop getFinalStop() {
        return finalStop;
    }

    public void setFinalStop(Stop finalStop) {
        this.finalStop = finalStop;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getMapboxResponse() {
        return mapboxResponse;
    }

    public void setMapboxResponse(String mapboxResponse) {
        this.mapboxResponse = mapboxResponse;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}