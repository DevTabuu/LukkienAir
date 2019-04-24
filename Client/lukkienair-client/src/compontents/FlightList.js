import React, { Component } from 'react'
import Flight from "./Flight"

export class FlightList extends Component {

  render() {
    return (
      <table>
          <tbody>
            <tr>
              <th>Flight ID</th>
              <th>From</th>
              <th>To</th>
              <th>Date (dd-mm-yyyy)</th>
              <th>Departing Time (24h)</th>
              <th>Flight Duration (hh:MM)</th>
              <th>Price</th>
            </tr>
            {
                this.props.flights.map(flight => <Flight key={flight.id} flight={flight}/>)
            }
          </tbody>
      </table>
    )
  }
}

export default FlightList
