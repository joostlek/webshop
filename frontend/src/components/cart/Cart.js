import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.css";
import CartLine from "./CartLine";

class Cart extends Component {
  constructor(props) {
    super(props);

    this.deleteHandler = this.deleteHandler.bind(this);
  }


  getCart() {
    return JSON.parse(sessionStorage["cart"]);
  }


  deleteHandler() {
    this.forceUpdate();
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
