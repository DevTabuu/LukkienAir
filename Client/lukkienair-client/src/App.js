import React, { Component } from 'react';
import './App.css';
import FlightList from './compontents/FlightList';
import Client from './compontents/Client';
import DestinationSelector from './compontents/DestinationSelector';

class App extends Component {

  constructor(props){
    super(props);
    this.client = new Client();
    this.state = {
      flights: [],
      destinations: []
    };

    this.client.destinations().then(r => this.setState({destinations: r}));
  }

  handleSubmit = e => {
    this.client.flights(
      this.originElement.selector.value, 
      this.destinationElement.selector.value, 
      this.minElement.value, 
      this.maxElement.value,
      this.dateElement.value
      ).then(r => this.setState({flights: r}));
  }

  render() {
    return (
      <div className="App">
        <div className="form">
          <label htmlFor="origin">From</label>
          <DestinationSelector id="origin" destinations={this.state.destinations} ref={el => this.originElement = el} />

          <label htmlFor="destination">To</label>
          <DestinationSelector id="destination" destinations={this.state.destinations} ref={el => this.destinationElement = el} />
          <br />

          <label htmlFor="departureDate">Departure Date</label>
          <input id="departureDate" ref={el => this.dateElement = el} type="date" name="departureDate" />
          <br />

          <input id="costMin" ref={el => this.minElement = el} type="number" placeholder="Minimum Price" name="costMin" />

          <input id="costMax" ref={el => this.maxElement = el} type="number" placeholder="Maximum Price" name="costMax" />
          <br />

          <input type="submit" value="Search..." onClick={this.handleSubmit} />
        </div>

        <FlightList flights={this.state.flights} />
      </div>
    );
  }
}

export default App;
