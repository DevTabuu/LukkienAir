package nl.tabuu.lukkienair.service.implementation;

import nl.tabuu.lukkienair.entity.FlightRoute;
import nl.tabuu.lukkienair.repository.IFlightRouteRepository;
import nl.tabuu.lukkienair.service.IFlightRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightRouteService implements IFlightRouteService {

    @Autowired
    private IFlightRouteRepository flightRouteRepository;

    @Override
    public List<FlightRoute> getFlightRouteList() {
        List<FlightRoute> flightRouteList = new ArrayList<>();
        flightRouteRepository.findAll().forEach(flightRouteList::add);
        return flightRouteList;
    }

    @Override
    public FlightRoute findFlightRouteById(long id) {
        return flightRouteRepository.findById(id).orElse(null);
    }

    @Override
    public FlightRoute findFlightRoute(long originId, long destinationId) {
        return flightRouteRepository.findFlightRoute(originId, destinationId);
    }

    @Override
    public List<FlightRoute> findFlightRoutesByOriginId(long originId) {
        return flightRouteRepository.findFlightRoutesByOriginId(originId);
    }

    @Override
    public List<FlightRoute> findFlightRoutesByDestinationId(long destinationId) {
        return flightRouteRepository.findFlightRoutesByDestinationId(destinationId);
    }

}
