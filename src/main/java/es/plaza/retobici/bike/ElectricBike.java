package es.plaza.retobici.bike;

import es.plaza.retobici.stop.Stop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "electric_bikes")
public class ElectricBike extends Bike{
    @Column(name = "battery") private Integer battery;

    public ElectricBike() {
    }

    public ElectricBike(Integer battery) {
        this.battery = battery;
    }

    public ElectricBike(Long id, Stop stop, Integer battery) {
        super(id, stop);
        this.battery = battery;
    }

    public ElectricBike(Stop stop, Integer battery) {
        super(stop);
        this.battery = battery;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }
}
