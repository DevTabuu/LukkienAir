package nl.tabuu.lukkienair.repository;

import nl.tabuu.lukkienair.entity.FlightRoute;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IFlightRouteRepository extends CrudRepository<FlightRoute, Long> {

    @Query("SELECT fr FROM FlightRoute fr WHERE fr.origin.id = ?1")
    List<FlightRoute> findFlightRoutesByOriginId(long originId);

    @Query("SELECT fr FROM FlightRoute fr WHERE fr.destination.id = ?1")
    List<FlightRoute> findFlightRoutesByDestinationId(long destinationId);

    @Query("SELECT fr FROM FlightRoute fr WHERE fr.origin.id = ?1 AND fr.destination.id = ?2")
    FlightRoute findFlightRoute(long originId, long destinationId);

}
