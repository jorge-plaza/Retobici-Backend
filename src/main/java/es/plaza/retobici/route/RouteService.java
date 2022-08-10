package es.plaza.retobici.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {this.routeRepository = routeRepository;}


    public List<Route> getAllRoutes(){ return routeRepository.findAll(); }
    public Route getRouteById(Long routeId){
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isEmpty()){
            throw new IllegalStateException("No exixting route with that ID");
        }
        return optionalRoute.get();
    }

    public List<Route> getRoutesByRider(Long riderId){ return routeRepository.findRouteByRider(riderId); }
}
