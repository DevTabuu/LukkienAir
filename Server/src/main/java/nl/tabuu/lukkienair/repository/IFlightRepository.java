package nl.tabuu.lukkienair.repository;

import nl.tabuu.lukkienair.entity.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
@Transactional
public interface IFlightRepository extends CrudRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.route.id = ?1 AND f.departureTime BETWEEN ?2 AND ?3 AND f.cost BETWEEN ?4 AND ?5")
    List<Flight> findFlights(long routeId, Date departureDateMin, Date departureDateMax, double costMin, double costMax);

    @Query("SELECT f FROM Flight f WHERE f.route.id = ?1 AND f.cost BETWEEN ?2 AND ?3")
    List<Flight> findFlights(long routeId, double costMin, double costMax);

    @Query("SELECT f FROM Flight f WHERE f.cost BETWEEN ?1 AND ?2")
    List<Flight> findFlights(double costMin, double costMax);

    @Query("SELECT f FROM Flight f WHERE f.route.id = ?1 AND  f.departureTime BETWEEN ?2 AND ?3")
    List<Flight> findFlights(long routeId, Date departureDateMin, Date departureDateMax);

    @Query("SELECT f FROM Flight f WHERE f.departureTime BETWEEN ?1 AND ?2")
    List<Flight> findFlights(Date departureDateMin, Date departureDateMax);

    @Query("SELECT f FROM Flight f WHERE f.route.id = ?1")
    List<Flight> findFlights(long routeId);

    Flight findTopByOrderByCostDesc();
}
