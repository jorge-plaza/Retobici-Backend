package es.plaza.retobici.bike;

import es.plaza.retobici.stop.Stop;
import org.springframework.data.jpa.domain.Specification;

public class BikeSpecification {

    public static Specification<Bike> searchByType(Class<? extends Bike> bikeType, Stop stop){
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("stop"), stop),
                criteriaBuilder.equal(root.type(), bikeType)
        );
    }
}
