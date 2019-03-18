import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css";
import CartLine from './CartLine';

class Cart extends Component {

    getCart() {
        return JSON.parse(window.sessionStorage.getItem("cart"));
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
        if (! sessionStorage["cart"]) {
            sessionStorage[ "cart"] = JSON.stringify(testdata);
        }
    }

    render() {
        this.loadTestData();

        return (
            <div>

                <div>
                    {this.getCart().map( item => (
                        <CartLine id={item.id} name={item.name} amount={item.amount} price_per_unit={item.price_per_unit} />
                    ))}
                </div>
                <button>koop</button>
            </div>
        );
    }

}

export default Cart;