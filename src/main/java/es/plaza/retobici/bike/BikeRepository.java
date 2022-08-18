package es.plaza.retobici.bike;

import es.plaza.retobici.stop.Stop;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long>, JpaSpecificationExecutor<Bike> {

    @Query("select b from Bike b WHERE TYPE(b) IN :bikeT and b.stop = :stop")
    Bike getBikeWithTypeInStop(@Param("bikeT") Class<Bike> bikeT, @Param("stop") Stop stopId);

}
