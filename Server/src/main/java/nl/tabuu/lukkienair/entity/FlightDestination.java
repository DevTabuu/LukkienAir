package nl.tabuu.lukkienair.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="destinations")
public class FlightDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = FlightRoute.class, mappedBy = "origin", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FlightRoute> flightRoutes;

    protected FlightDestination(){}

    public FlightDestination(String name) {
        this.name = name;
        this.flightRoutes = new HashSet<>();
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Set<FlightRoute> getFlightRoutes(){
        return flightRoutes;
    }

    public void addFlightRoute(FlightRoute route) {
        flightRoutes.add(route);
    }

}
