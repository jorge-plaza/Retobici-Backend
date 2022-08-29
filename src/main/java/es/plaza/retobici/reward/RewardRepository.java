package es.plaza.retobici.reward;

import es.plaza.retobici.user.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward,Long> {
    public List<Reward> findAllByRiders(Rider rider);
}
