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
        sessionStorage["cart"] = JSON.stringify(object);
    }

    deleteItem(productId) {
        let cart = this.getCart();
        for(let i=0; i<cart.length; i++) {
            if (cart[i].id === productId) {
                cart.splice(i, 1);
                break;
            }
        }
        this.updateSessionStorage(cart);
        this.props.deleteHandler();
    }

    updateCart() {
        let cart = this.getCart();
        for(let i=0; i<cart.length;i++) {
            if (cart[i].id === this.props.id) {

                cart[i].amount = this.state.amount;

                break;
            }
        }
        this.updateSessionStorage(cart);
    }



    async increaseAmount() {
        await this.setState({amount: this.state.amount + 1} );
        this.updateCart();
    }

    async decreaseAmount() {
        if (this.state.amount <= 1) {
            await this.setState( {amount: 1} )
        } else {
            await this.setState({amount: this.state.amount - 1});
        }
        this.updateCart();
    }


    render() {
        return (
            <div className="row" style={{ margin: "1em 0em 1em 0em" }}>
                <div className="col-md-5">{this.props.name}</div>
                <div className="col-md-3">
                    <button className="btn btn-warning" onClick={() => this.decreaseAmount()} style={{ margin:"0em .5em 0em .5em" }}>-</button>
                    {this.state.amount}
                    <button className="btn btn-success" onClick={() => this.increaseAmount()} style={{ margin:"0em .5em 0em .5em" }}>+</button>
                </div>
                <div className="col-md-2">€{(this.state.amount * this.props.price).toFixed(2)},-</div>
                <div className="col-md-1">
                    <button className="btn btn-danger" onClick={() => this.deleteItem(this.props.id)}>X</button>
                </div>
            </div>
        );
    }

}

export default CartLine;