package es.plaza.retobici.route.mapbox;

import java.util.List;
import java.util.UUID;

public record MapboxResponse(
        List<SuggestedRoute> routes,
        List<Object> waypoints,
        String code,
        String uuid
) {
    public Float getDistance(){
        return routes.get(0).distance();
    }

    public Float getDuration(){
        return routes.get(0).duration();
    }
}
