package es.plaza.retobici.route;

import es.plaza.retobici.bike.Bike;
import es.plaza.retobici.di.MapboxClient;
import es.plaza.retobici.exception.ApiRequestException;
import es.plaza.retobici.spot.Spot;
import es.plaza.retobici.stop.Stop;
import es.plaza.retobici.user.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    private final MapboxClient mapboxClient;

    @Autowired
    public RouteService(RouteRepository routeRepository, MapboxClient mapboxClient) {
        this.routeRepository = routeRepository;
        this.mapboxClient = mapboxClient;
    }



    public List<Route> getAllRoutes(){ return routeRepository.findAll(); }
    public Route getRouteById(Long routeId){
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isEmpty()){
            throw new IllegalStateException("No exixting route with that ID");
        }
        return optionalRoute.get();
    }

    public List<Route> getRoutesByRider(Long riderId){ return routeRepository.findRouteByRider(riderId); }

    public Route startRoute(Rider rider, Stop stop, Bike bike) {
        Route newRoute = new Route(rider, stop, bike);
        return routeRepository.save(newRoute);
    }

    public Route finishRoute(Long routeId, Spot spot) {
        Route route = routeRepository.findById(routeId).orElseThrow(() -> new ApiRequestException("No Route for that ID"));
        Stop initialStop = route.getInitialStop();
        Stop finalStop =  spot.getStop();


        WebClient client = WebClient.create();
        Double lng1 = initialStop.getLng();
        Double lng2 = finalStop.getLng();
        Double lat1 = initialStop.getLat();
        Double lat2 = finalStop.getLat();
        String coordenates = lng1+","+lat1+";"+lng2+","+lat2;
        String token = mapboxClient.token;
        ResponseEntity<String> res = mapboxClient.client.get()
                .uri("https://api.mapbox.com/directions/v5/mapbox/cycling/"+coordenates+"?alternatives=false&geometries=geojson&overview=full&steps=false&access_token="+token)
                .retrieve()
                .toEntity(String.class)
                .block();
        System.out.println(res);
        return null;
    }
}
