import React, {Component} from "react";
import "../App.css";
import "./product.css"
import "bootstrap/dist/css/bootstrap.css";
import productimage from "../assets/product.jpg";
// import "./components/variables.js";

class Product extends Component {

    constructor(props) {
        super(props);

        this.state = {
            amount: 0,
            data: []
        }

        this.handleChange = this.handleChange.bind(this);

    }

    handleChange(event) {
        this.setState({
            amount: event.target.value
        });
    }

    componentDidMount () {
        var fetchoptions = {method: 'GET'};
        fetch("http://localhost:8082/products/" + this.props.match.params.id, fetchoptions)
            .then((response) => {
                return response.json();
            })
            .then( (response) => {
                this.setState({data: response});
            })
    }

    saveProductToSession()  {
        let cart = sessionStorage["cart"] ? JSON.parse(sessionStorage["cart"]) : [];

        let product = {
            id: this.state.data.id,
            name: this.state.data.title,
            price: this.state.data.price,
            amount: this.state.amount
        };

        cart.push(product);

        cart = JSON.stringify(cart);

        sessionStorage["cart"] = cart;
    }

    render() {
        let test;

        if (!this.state.data) {
            test = (<div />);
        } else {
            test = (

                    <div className="container-fluid">
                        <div className="content-wrapper">
                            <div className="item-container">
                                <div className="container">
                                    <div className="col-md-6 product-page__container">
                                        <div className="product">

                                            <img id="item-display" src={productimage} alt="product"/>
                                        </div>

                                        <div id="title" ref="title"><h2>{this.state.data.title}</h2></div>
                                        <div className="product-desc">{this.state.data.description}</div>
                                        <div className="col-md-3"> </div>
                                        <div className="product-price">€ {this.state.data.price}</div>
                                        <p>aantal</p>
                                        <input className="product-amount" type="number" value={this.state.amount} onChange={this.handleChange} /><br />
                                        <div className="btn-group cart">
                                            <button type="button" className="btn btn-success btn-cart" onClick={()=>this.saveProductToSession()} >
                                                Voeg toe aan winkelwagen
                                            </button>
                                        </div>
                                        <div className="btn-group wishlist">
                                            <button type="button" className="btn btn-danger">
                                                Terug naar de categorie
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            );
        }


        return (
            <div>
                {test}
            </div>
        );
    }
}

export default Product;
