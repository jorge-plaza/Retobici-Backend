package es.plaza.retobici.bike;

import es.plaza.retobici.spot.Spot;
import es.plaza.retobici.stop.Stop;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class BikeSpecification {

    public static Specification<Bike> searchByType(Class<? extends Bike> bikeType, Stop stop){
        return (root, query, criteriaBuilder) -> {
            Join<Bike,Spot> join = root.join("spot");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(join.get("stop"), stop),
                    criteriaBuilder.equal(root.type(), bikeType)
            );
        };
    }
}
