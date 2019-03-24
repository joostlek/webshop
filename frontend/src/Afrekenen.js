import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.css";

export class Afrekenen extends Component {
  render() {
    return (
      <div className="container">
        <label className="row">Naam</label>
        <input placeholder="Voor- en achternaam" />
        <label className="row">Postcode</label>
        <input placeholder="1234AB" />
        <label className="row">Huisnummer</label>
        <input placeholder="12A" />

        <div className="dropdown-divider" />

        <label>Winkelwagen shit</label>
      </div>
    );
  }
}

export default Afrekenen;
