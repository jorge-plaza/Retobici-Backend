package es.plaza.retobici.reward;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rewards")
public class RewardController {

    private final RewardService rewardService;

    private final ModelMapper modelMapper;

    @Autowired
    public RewardController(RewardService rewardService, ModelMapper modelMapper) {
        this.rewardService = rewardService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Reward> getRewards(){ return rewardService.getAllRewards(); }

    @GetMapping(path = "/rider/{riderId}")
    public ResponseEntity<List<RewardDto>> getRiderRewards(
            @PathVariable(name = "riderId") Long riderId
    ){
        List<RewardDto> result = rewardService.getRiderRedeemedRewards(riderId).stream()
                .map(reward -> modelMapper.map(reward, RewardDto.class))
                .toList();
        return ResponseEntity.ok(result);
    }
}
