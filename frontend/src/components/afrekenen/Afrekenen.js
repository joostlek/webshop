import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.css";
import CartLine from "../cart/CartLine";
import { NavLink } from "react-router-dom";

export class Afrekenen extends Component {
  constructor(props) {
    super(props);

    this.state = {
      checkbox: false
    };
  }

  getCart() {
    return JSON.parse(sessionStorage["cart"]);
  }

  handleCheckbox = event => {
    this.setState({
      checkbox: event.target.value
    });
  };

  rekenAf = () => {
    if (this.state.checkbox) {
      this.checkboxConfirmed();
    }
  };

  checkboxConfirmed() {
    console.log("test");
    window.replace("localhost:8080/order/{orderId}");
  }

  render() {
    return (
      <div className="container">
        <h4>Persoonsgegevens</h4>
        <label className="row">Achternaam</label>
        <input placeholder="Achternaam" />
        <label className="row">Voornaam</label>
        <input placeholder="Voornaam" />

        <div className="dropdown-divider" />

        <h4>Adresgegevens</h4>
        <label className="row">Straatnaam</label>
        <input placeholder="Straatnaam" />
        <label className="row">Huisnummer</label>
        <input placeholder="12A" />
        <label className="row">Postcode</label>
        <input placeholder="1234AB" />
        <label className="row">Land</label>
        <input placeholder="Nederland" />

        <div className="dropdown-divider" />

        <h4>Winkelwagen</h4>
        <div>
          {this.getCart().map(item => (
            <CartLine
              key={item.id}
              id={item.id}
              name={item.name}
              amount={item.amount}
              price={item.price}
              deleteHandler={this.deleteHandler}
            />
          ))}
        </div>

        <div className="dropdown-divider" />
        <br />
        <div>
          <label>Akkoord </label>
          <br />
          <input
            type="checkbox"
            value={this.state.checkbox}
            onClick={this.handleCheckbox}
          />
        </div>
        <br />
        <div>
          <button
            className="btn btn-success"
            onClick={() => this.checkboxConfirmed}
          >
            Bestelling afronden
          </button>
        </div>
      </div>
    );
  }
}

export default Afrekenen;
