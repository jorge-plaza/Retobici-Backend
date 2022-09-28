package es.plaza.retobici.reservation;

import es.plaza.retobici.user.rider.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    public List<Reservation> findByRider(Long rider);

    public Optional<Reservation> findByRiderAndIsActiveIsTrue(Rider rider);
}
