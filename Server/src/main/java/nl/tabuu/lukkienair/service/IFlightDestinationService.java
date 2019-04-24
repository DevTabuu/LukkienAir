package nl.tabuu.lukkienair.service;

import nl.tabuu.lukkienair.entity.FlightDestination;

import java.util.List;

public interface IFlightDestinationService {

    List<FlightDestination> getFlightDestinationList();

    FlightDestination findFlightDestinationById(long id);

    FlightDestination findFlightDestinationByName(String name);

}
