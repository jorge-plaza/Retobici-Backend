package es.plaza.retobici.user;

import es.plaza.retobici.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RiderService {

    private final RiderRepository riderRepository;

    @Autowired
    public RiderService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public List<Rider> getRiders(){
        return riderRepository.findAll();
    }

    public Rider getRider(Long riderId){
        return riderRepository.findById(riderId).orElseThrow(
                () -> new ApiRequestException("Rider with that id does not exists"));
    }

    public void addnewRider(Rider rider) {
        Optional<Rider> riderByEmail = riderRepository.findRiderByEmail(rider.getEmail());
        if (riderByEmail.isEmpty()){
            throw new IllegalStateException("email already exists");
        }
        riderRepository.save(rider);
    }

    public void deleteRider(Long riderId) {
        if (! riderRepository.existsById(riderId)){
            throw new IllegalStateException("student does not exists");
        }
        riderRepository.deleteById(riderId);
    }

    @Transactional
    public void updateRider(Long riderId, String name, String email) {
        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new IllegalStateException("student with that id does not exists"));

        if (name != null && !name.isEmpty()) rider.setName(name);
        if (email != null && !email.isEmpty()){
            if (riderRepository.existsById(riderId)) throw new IllegalStateException("new email already taken");
            rider.setEmail(email);
        }
    }
}
