package nl.tabuu.lukkienair.service.implementation;

import nl.tabuu.lukkienair.entity.Flight;
import nl.tabuu.lukkienair.repository.IFlightRepository;
import nl.tabuu.lukkienair.repository.IFlightRouteRepository;
import nl.tabuu.lukkienair.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService implements IFlightService {

    @Autowired
    private IFlightRepository flightRepository;

    @Autowired
    private IFlightRouteRepository flightRouteRepository;

    @Override
    public Flight findMostExpensiveFlight() {
        return flightRepository.findTopByOrderByCostDesc();
    }

    @Override
    public Flight findFlightById(long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public List<Flight> getFlightList() {
        List<Flight> flightList = new ArrayList<>();
        flightRepository.findAll().forEach(flightList::add);
        return flightList;
    }

    @Override
    public List<Flight> findFlights(long routeId, Date departureDate, double costMin, double costMax) {
        Date departureDateMin = floorDate(departureDate);
        Date departureDateMax = ceilDate(departureDate);

        return flightRepository.findFlights(routeId, departureDateMin, departureDateMax, costMin, costMax);
    }

    @Override
    public List<Flight> findFlights(long routeId, double costMin, double costMax) {
        return flightRepository.findFlights(routeId, costMin, costMax);
    }

    @Override
    public List<Flight> findFlights(double costMin, double costMax) {
        return flightRepository.findFlights(costMin, costMax);
    }

    @Override
    public List<Flight> findFlights(long routeId, Date departureDate) {
        Date departureDateMin = floorDate(departureDate);
        Date departureDateMax = ceilDate(departureDate);

        return flightRepository.findFlights(routeId, departureDateMin, departureDateMax);
    }

    @Override
    public List<Flight> findFlights(Date departureDate) {
        Date departureDateMin = floorDate(departureDate);
        Date departureDateMax = ceilDate(departureDate);

        return flightRepository.findFlights(departureDateMin, departureDateMax);
    }

    @Override
    public List<Flight> findFlights(long routeId) {
        return flightRepository.findFlights(routeId);
    }

    private Date ceilDate(Date date){
        long timestamp = date.getTime();
        long remnant = timestamp % 86400000L;
        return new Date(timestamp + (86400000L - remnant) + 86400000L);
    }

    private Date floorDate(Date date){
        long timestamp = date.getTime();
        long remnant = timestamp % 86400000L;
        return new Date(timestamp - remnant + 86400000L);
    }
}
