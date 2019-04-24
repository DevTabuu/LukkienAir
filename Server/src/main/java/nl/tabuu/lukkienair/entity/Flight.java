package nl.tabuu.lukkienair.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Type(type = "timestamp")
    @Column(name = "departure_time")
    private Date departureTime;

    @Column(name = "cost")
    private double cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FlightRoute route;

    protected Flight(){}

    public Flight(FlightRoute route, Date departureTime, double cost){
        this.departureTime = departureTime;
        this.cost = cost;
        this.route = route;

        this.route.addFlight(this);
    }

    public long getId() {
        return this.id;
    }

    public Date getDepartureTime(){
        return this.departureTime;
    }

    public double getCost() {
        return this.cost;
    }

    public FlightRoute getRoute() {
        return route;
    }

}
