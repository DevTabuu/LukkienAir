package nl.tabuu.lukkienair.service.implementation;

import nl.tabuu.lukkienair.entity.FlightDestination;
import nl.tabuu.lukkienair.repository.IFlightDestinationRepository;
import nl.tabuu.lukkienair.service.IFlightDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightDestinationService implements IFlightDestinationService {

    @Autowired
    private IFlightDestinationRepository flightDestinationRepository;

    @Override
    public List<FlightDestination> getFlightDestinationList() {
        List<FlightDestination> flightDestinationList = new ArrayList<>();
        flightDestinationRepository.findAll().forEach(flightDestinationList::add);
        return flightDestinationList;
    }

    @Override
    public FlightDestination findFlightDestinationById(long id) {
        return flightDestinationRepository.findById(id).orElse(null);
    }

    @Override
    public FlightDestination findFlightDestinationByName(String name) {
        return flightDestinationRepository.findFlightDestination(name);
    }

}
