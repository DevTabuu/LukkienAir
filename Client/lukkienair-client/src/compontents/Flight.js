import React, { Component } from 'react'
import Client from './Client';

export class Flight extends Component {

  constructor(props){
    super(props);

    this.client = new Client();

    this.state = {
      originName: this.props.flight.route.origin.name,
      destinationName: this.props.flight.route.destination.name
    }
  }

  render() {
    return (
      <tr>
        {
          [
          <td key="0"><a target="_blank" rel="noopener noreferrer" href={`http://www.duckduckgo.com?q=flight+from+${this.props.flight.route.origin.name}+to+${this.props.flight.route.destination.name}`}>{this.props.flight.id}</a></td>,
          <td key="1">{this.props.flight.route.origin.name}</td>,
          <td key="2">{this.props.flight.route.destination.name}</td>,
          <td key="3">{this.dateString(this.formattedDate(this.props.flight.departureTime))}</td>,
          <td key="4">{this.timeString(this.formattedDate(this.props.flight.departureTime))}</td>,
          <td key="5">{this.minuteFormat(this.props.flight.route.duration)}</td>,
          <td key="6">€ {this.props.flight.cost.toFixed(2)}</td>
          ]
        }
      </tr>
    )
  }

  dateString(date){
    return `${this.numberFormat(date.getDate())}-${this.numberFormat(date.getMonth())}-${date.getFullYear()}`;
  }

  timeString(date){
    return `${this.numberFormat(date.getHours())}:${this.numberFormat(date.getMinutes())}`
  }

  formattedDate(dateTime){
    const dateTimeParts = dateTime.split(/[-T:+]/);
    return new Date(...dateTimeParts);
  }

  numberFormat(number){
    return ("0" + number).slice(-2);
  }

  minuteFormat(number){

    let hours = Math.floor(number / 60);
    let minutes = number % 60;

    return this.numberFormat(hours) + ":" + this.numberFormat(minutes); 

  }
}

export default Flight
