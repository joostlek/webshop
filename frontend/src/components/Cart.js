import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css";
import CartLine from './CartLine';

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
                "id": 1,
                "name": "Komkommer",
                "amount": 3,
                "price_per_unit": 1.00
            },
            {
                "id": 2,
                "name": "Chips",
                "amount": 2,
                "price_per_unit": 1.20
            }
        ];
        //if (! sessionStorage["cart"]) {
            sessionStorage[ "cart"] = JSON.stringify(testdata);
        //}
    }

    render() {
        return (
            <div>
                <div>
                    {this.getCart().map( item => (
                        <CartLine id={item.id} name={item.name} amount={item.amount}
                                  price_per_unit={item.price_per_unit} deleteHandler={this.deleteHandler} />
                    ))}
                </div>
                <button className="btn btn-primary">koop</button>
            </div>
        );
    }

}

export default Cart;