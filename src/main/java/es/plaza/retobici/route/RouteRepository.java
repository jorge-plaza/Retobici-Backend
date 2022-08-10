package es.plaza.retobici.route;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.stop.Stop;
import es.plaza.retobici.user.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findRouteByRider(Long riderId);
    Optional<List<Route>> findRouteByBike(Bike bike);
    Optional<List<Route>> findRouteByInitialStop(Stop stop);
    Optional<List<Route>> findRouteByFinalStop(Stop stop);
}
