import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.css";
import "./Cart.css";
import CartLine from "./CartLine";

class Cart extends Component {
  constructor(props) {
    super(props);
    this.state = {
      active: true
    };
  }

  getCart() {
    return JSON.parse(window.sessionStorage.getItem("cart"));
  }

  loadTestData() {
    let testdata = [
      {
        id: 1,
        name: "Komkommer",
        amount: 3,
        price_per_unit: 1.0
      },
      {
        id: 2,
        name: "Chips",
        amount: 2,
        price_per_unit: 1.2
      }
    ];
    if (!sessionStorage["cart"]) {
      sessionStorage["cart"] = JSON.stringify(testdata);
    }
  }

  toggleClass = () => {
    const currentState = this.state.active;
    this.setState({ active: !currentState });
  };

  render() {
    this.loadTestData();

    return (
      <div className="container">
        <a onClick={this.toggleClass}>
          <i>Cart</i> <span className="badge badge-secondary">2</span>
        </a>

        <div className={this.state.active ? "collapse" : null}>
          <div>
            {this.getCart().map(item => (
              <CartLine
                key={item.id}
                id={item.id}
                name={item.name}
                amount={item.amount}
                price_per_unit={item.price_per_unit}
              />
            ))}
          </div>
        </div>
      </div>
    );
  }
}

export default Cart;
