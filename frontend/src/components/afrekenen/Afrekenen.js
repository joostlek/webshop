import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.css";

export class Afrekenen extends Component {
    render() {
        // console.log(JSON.parse(sessionStorage["cart"]));
        return (
            <div className="container">
                <h3>Persoonsgegevens</h3>
                <label className="row">Achternaam</label>
                <input placeholder="Achternaam" />
                <label className="row">Voornaam</label>
                <input placeholder="Voornaam" />

                <div className="dropdown-divider" />

                <h3>Adresgegevens</h3>
                <label className="row">Straatnaam</label>
                <input placeholder="Straatnaam" />
                <label className="row">Huisnummer</label>
                <input placeholder="12A" />
                <label className="row">Postcode</label>
                <input placeholder="1234AB" />
                <label className="row">Land</label>
                <input placeholder="Nederland" />

                <div className="dropdown-divider" />

                <h3>Winkelwagen</h3>
            </div>
        );
    }
}

export default Afrekenen;