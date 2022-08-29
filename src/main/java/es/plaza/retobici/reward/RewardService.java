package es.plaza.retobici.reward;

import es.plaza.retobici.user.Rider;
import es.plaza.retobici.user.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

    private final RewardRepository rewardRepository;

    private final RiderService riderService;

    @Autowired
    public RewardService(RewardRepository rewardRepository, RiderService riderService) {
        this.rewardRepository = rewardRepository;
        this.riderService = riderService;
    }

    public List<Reward> getAllRewards(){ return rewardRepository.findAll(); }

    public List<Reward> getRiderRedeemedRewards(Long riderId){
        Rider rider =  riderService.getRider(riderId);
        return rewardRepository.findAllByRiders(rider);
    }
}
