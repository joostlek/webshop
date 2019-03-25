import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.css";
import CartLine from "../cart/CartLine";

export class Afrekenen extends Component {
  constructor(props) {
    super(props);

    this.state = {
      checked: false
    };
  }

  getCart() {
    return JSON.parse(sessionStorage["cart"]);
  }

  handleCheckbox = () => {
    this.setState({
      checked: !this.state.checked
    });
  };

  cartIntoJson() {
    let products = [];
    for (let i = 0; i < this.getCart().length; i++) {
      products.push({
        productId: this.getCart()[i].id,
        amount: this.getCart()[i].amount
      });
    }
    return products;
  }

  rekenAf = () => {
    if (this.state.checked) {
      let fetchoptions = {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          orderItems: this.cartIntoJson(),
          addressId: 1,
          userId: 1
        })
      };

      fetch("http://localhost:8082/orders", fetchoptions)
        .then(function(response) {
          if (response.ok) return response.json();
          else throw "Er is iets fout gegaan met afronden van uw order.";
        })
        .then(response => {
          alert(response.title + "is besteld");
        })
        .catch(function(error) {
          console.log(error);
        });

      window.replace("http://localhost:8082/order/" + response.id);
    } else {
      alert("U moet eerst akkoord gaan voordat u de bestelling kunt afronden");
    }
  };

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
            onChange={this.handleCheckbox}
          />
        </div>
        <br />
        <div>
          <button className="btn btn-success" onClick={this.rekenAf}>
            Bestelling afronden
          </button>
        </div>
      </div>
    );
  }
}

export default Afrekenen;
