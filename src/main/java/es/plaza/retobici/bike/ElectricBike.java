package es.plaza.retobici.bike;

import es.plaza.retobici.spot.Spot;
import es.plaza.retobici.stop.Stop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "electricbike")
public class ElectricBike extends Bike{
    @Column(name = "battery") private Integer battery;

    public ElectricBike() {
    }

    public ElectricBike(Integer battery) {
        this.battery = battery;
    }

    public ElectricBike(Long id, Spot spot, Integer battery) {
        super(id, spot);
        this.battery = battery;
    }

    public ElectricBike(Spot spot, Integer battery) {
        super(spot);
        this.battery = battery;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }
}
