package nl.tabuu.lukkienair.controller;

import nl.tabuu.lukkienair.entity.Flight;
import nl.tabuu.lukkienair.entity.FlightRoute;
import nl.tabuu.lukkienair.service.implementation.FlightRouteService;
import nl.tabuu.lukkienair.service.implementation.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.sql.Date;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRouteService flightRouteService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Flight> getFlights(
            @RequestParam(value = "costMin", required = false, defaultValue = "0") double costMin,
            @RequestParam(value = "costMax", required = false, defaultValue = "0") double costMax,
            @RequestParam(value = "originId", required = false, defaultValue = "-1") long originId,
            @RequestParam(value = "destinationId", required = false, defaultValue = "-1") long destinationId,
            @RequestParam(value = "departureDate", required = false, defaultValue = "1970-01-01") Date departureDate){

        if(costMin > 0 && costMax == 0)
            costMax = flightService.findMostExpensiveFlight().getCost();

        // Use: origin, destination, departure date, and cost
        if(originId >= 0 && destinationId >= 0  && departureDate.getTime() > 0 && (costMin > 0 || costMax > 0)){
            FlightRoute flightRoute = flightRouteService.findFlightRoute(originId, destinationId);
            return flightRoute == null ? Collections.emptyList() :
                                         flightService.findFlights(flightRoute.getId(), departureDate, costMin, costMax);
        }
        // Use: origin, destination, and cost
        else if(originId >= 0 && destinationId >= 0 && (costMin > 0 || costMax > 0)){
            FlightRoute flightRoute = flightRouteService.findFlightRoute(originId, destinationId);
            return flightRoute == null ? Collections.emptyList() :
                    flightService.findFlights(flightRoute.getId(), costMin, costMax);
        }
        // Use: origin, destination, and departure date
        else if(originId >= 0 && destinationId >= 0 && departureDate.getTime() > 0){
            FlightRoute flightRoute = flightRouteService.findFlightRoute(originId, destinationId);
            return flightRoute == null ? Collections.emptyList() :
                    flightService.findFlights(flightRoute.getId(), departureDate);
        }
        // Use: origin and destination
        else if(originId >= 0 && destinationId >= 0){
            FlightRoute flightRoute = flightRouteService.findFlightRoute(originId, destinationId);
            return flightRoute == null ? Collections.emptyList() :
                                         flightService.findFlights(flightRoute.getId());
        }
        // Use: case when there can be multiple routes
        else if(originId >= 0 || destinationId >= 0){
            List<FlightRoute> routes = new ArrayList<>();
            List<Flight> flights = new ArrayList<>();

            routes.addAll(originId >= 0 ? flightRouteService.findFlightRoutesByOriginId(originId) :
                                          flightRouteService.findFlightRoutesByDestinationId(destinationId));

            for(FlightRoute route : routes){

                // Use: cost and departure date
                if((costMin > 0 || costMax > 0) && departureDate.getTime() > 0){
                    flights.addAll(flightService.findFlights(route.getId(), departureDate, costMin, costMax));
                }
                // Use: departure date
                else if(departureDate.getTime() > 0){
                    flights.addAll(flightService.findFlights(route.getId(), departureDate));
                }
                // Use: cost
                else if(costMin > 0 || costMax > 0){
                    flights.addAll(flightService.findFlights(route.getId(), costMin, costMax));
                }
                else{
                    flights.addAll(flightService.findFlights(route.getId()));
                }
            }

            return flights;
        }
        else if(costMin > 0 || costMax > 0){
            return flightService.findFlights(costMin, costMax);
        }

        return flightService.getFlightList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Flight getFlightById(@PathVariable("id") long id){
        return flightService.findFlightById(id);
    }

}
