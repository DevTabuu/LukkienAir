package nl.tabuu.lukkienair.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="routes")
public class FlightRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "duration")
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FlightDestination origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FlightDestination destination;

    @OneToMany(targetEntity = Flight.class, mappedBy = "route", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Flight> flights;

    protected FlightRoute(){}

    public FlightRoute(FlightDestination origin, FlightDestination destination, int duration) {
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;

        this.flights = new HashSet<>();
        this.origin.addFlightRoute(this);
    }

    public long getId(){
        return this.id;
    }

    public int getDuration() {
        return this.duration;
    }

    public FlightDestination getOrigin() {
        return origin;
    }

    public FlightDestination getDestination() {
        return destination;
    }

    public Set<Flight> getFlights(){
        return flights;
    }

    public void addFlight(Flight flight){
        flights.add(flight);
    }

}
