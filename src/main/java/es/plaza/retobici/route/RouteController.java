package es.plaza.retobici.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/routes")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) { this.routeService = routeService; }

    @GetMapping(path = "{routeId}")
    public Route getRouteById(@PathVariable Long routeId){
        return routeService.getRouteById(routeId);
    }
}
