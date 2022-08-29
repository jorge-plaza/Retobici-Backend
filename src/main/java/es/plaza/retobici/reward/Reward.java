package es.plaza.retobici.reward;

import es.plaza.retobici.user.Rider;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rewards")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reward_seq")
    @SequenceGenerator(name = "reward_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String Description;

    private Integer points;

    @Column(name = "total_available")
    private Integer totalAvailable;

    @ManyToMany(mappedBy = "rewardsRedeemed")
    private List<Rider> riders;

    public Reward() {
    }

    public Reward(Long id, String name, String description, Integer points, Integer totalAvailable, List<Rider> riders) {
        this.id = id;
        this.name = name;
        Description = description;
        this.points = points;
        this.totalAvailable = totalAvailable;
        this.riders = riders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getTotalAvailable() {
        return totalAvailable;
    }

    public void setTotalAvailable(Integer totalAvailable) {
        this.totalAvailable = totalAvailable;
    }

    public List<Rider> getRiders() {
        return riders;
    }

    public void setRiders(List<Rider> riders) {
        this.riders = riders;
    }
}