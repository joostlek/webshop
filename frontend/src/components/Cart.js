import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.css";
import CartLine from "./CartLine";

class Cart extends Component {
  constructor(props) {
    super(props);

    this.loadTestData();

    this.deleteHandler = this.deleteHandler.bind(this);
  }

  getCart() {
    return JSON.parse(sessionStorage["cart"]);
  }

  deleteHandler() {
    this.forceUpdate();
  }

  loadTestData() {
    let testdata = [
      {
        id: 1,
        name: "Komkommer",
        amount: 3,
        price: 1.0
      },
      {
        id: 2,
        name: "Chips",
        amount: 2,
        price: 1.2
      }
    ];
    //if (! sessionStorage["cart"]) {
    sessionStorage["cart"] = JSON.stringify(testdata);
    //}
  }

  render() {
    return (
      <div>
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
        <button className="btn btn-primary">Bestelling afronden</button>
      </div>
    );
  }
}

export default Cart;
