package nl.tabuu.lukkienair.service;

import nl.tabuu.lukkienair.entity.Flight;

import java.sql.Date;
import java.util.List;

public interface IFlightService {

    Flight findMostExpensiveFlight();

    Flight findFlightById(long id);

    List<Flight> getFlightList();

    List<Flight> findFlights(long routeId, Date departureDate, double costMin, double costMax);

    List<Flight> findFlights(long routeId, double costMin, double costMax);

    List<Flight> findFlights(double costMin, double costMax);

    List<Flight> findFlights(long routeId, Date departureDate);

    List<Flight> findFlights(Date departureDate);

    List<Flight> findFlights(long routeId);
}
