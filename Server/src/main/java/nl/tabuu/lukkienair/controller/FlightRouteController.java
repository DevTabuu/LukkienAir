package nl.tabuu.lukkienair.controller;

import nl.tabuu.lukkienair.entity.FlightRoute;
import nl.tabuu.lukkienair.service.implementation.FlightRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class FlightRouteController {

    @Autowired
    private FlightRouteService flightRouteService;

    @RequestMapping(method = RequestMethod.GET)
    public List<FlightRoute> getAllFlightRoutes(){
        return this.flightRouteService.getFlightRouteList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FlightRoute getFlightRouteById(@PathVariable("id") long id){
        return flightRouteService.findFlightRouteById(id);
    }
}

