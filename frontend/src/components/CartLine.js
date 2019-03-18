import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css";

class CartLine extends Component {

    constructor(props) {
        super(props);
        this.state = {
            amount: this.props.amount,
        };
    }

    getCart() {
        return JSON.parse(sessionStorage["cart"]);
    }

    updateSessionStorage(object) {
        let updatedCart = JSON.stringify(object);
        sessionStorage["cart"] = updatedCart;
    }

    deleteItem(productId) {
        let cart = this.getCart();
        for(let i=0; i<cart.length; i++) {
            if (cart[i].id === productId) {
                cart.splice(i, 1);
            }
        }
        this.updateSessionStorage(cart);
    }

    updateCart() {
        let cart = this.getCart();
        var j;
        for(let i=0; i<cart.length;i++) {
            if (cart[i].id === this.props.id) {
                if (this.state.amount <= 0) { // If amount reaches 0 delete product else update amount
                    this.deleteItem(i);
                } else {
                    cart[i].amount = this.state.amount;
                }
                break;
            }
        }
        this.updateSessionStorage(cart);
    }



    async increaseAmount() {
        await this.setState({amount: this.state.amount + 1});
        this.updateCart();
    }

    async decreaseAmount() {
        await this.setState({amount: this.state.amount - 1});
        this.updateCart();
    }


    render() {
        return (
            <div>
                <div>{this.props.name}</div>
                <div>
                    <button onClick={() => this.decreaseAmount()}>-</button>
                    {this.state.amount}
                    <button onClick={() => this.increaseAmount()}>+</button>
                </div>
                <div>€{(this.state.amount * this.props.price_per_unit).toFixed(2)},-</div>
            </div>
        );
    }

}

export default CartLine;