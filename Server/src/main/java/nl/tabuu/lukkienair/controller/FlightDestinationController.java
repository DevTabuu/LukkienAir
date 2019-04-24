package nl.tabuu.lukkienair.controller;

import nl.tabuu.lukkienair.entity.FlightDestination;
import nl.tabuu.lukkienair.service.implementation.FlightDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class FlightDestinationController {

    @Autowired
    private FlightDestinationService flightDestinationService;

    @RequestMapping(method = RequestMethod.GET)
    public List<FlightDestination> getAllFlightDestinations(){
        return this.flightDestinationService.getFlightDestinationList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FlightDestination getFlightDestinationById(@PathVariable("id") long id){
        return flightDestinationService.findFlightDestinationById(id);
    }

}

