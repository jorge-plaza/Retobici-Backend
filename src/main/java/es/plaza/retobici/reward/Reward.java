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

    @ManyToMany(mappedBy = "rewardsReedemed")
    private List<Rider> riders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}