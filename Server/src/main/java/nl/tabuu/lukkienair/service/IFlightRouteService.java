package nl.tabuu.lukkienair.service;

import nl.tabuu.lukkienair.entity.FlightRoute;

import java.util.List;

public interface IFlightRouteService {

    List<FlightRoute> getFlightRouteList();

    FlightRoute findFlightRouteById(long id);

    FlightRoute findFlightRoute(long originId, long destinationId);

    List<FlightRoute> findFlightRoutesByOriginId(long originId);

    List<FlightRoute> findFlightRoutesByDestinationId(long destinationId);

}
