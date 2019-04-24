package nl.tabuu.lukkienair.repository;

import nl.tabuu.lukkienair.entity.FlightDestination;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IFlightDestinationRepository extends CrudRepository<FlightDestination, Long> {

    @Query("SELECT fd FROM FlightDestination fd WHERE fd.name = ?1")
    FlightDestination findFlightDestination(String name);

}
