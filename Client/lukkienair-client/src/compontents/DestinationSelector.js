import React, { Component } from 'react'

export class DestinationSelector extends Component {

    render() {
      return (
          <select id={this.props.id} ref={el => this.selector = el}>
              <option value="-1">None</option>
              {
                this.props.destinations.map(destination => <option key={destination.id} value={destination.id}>{destination.name}</option>)
              }
          </select>
      )
    }
}

export default DestinationSelector
