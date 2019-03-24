import React, {Component} from "react";
import "../App.css";
import "./product.css"
import "bootstrap/dist/css/bootstrap.css";
import productimage from "../assets/product.jpg";
// import "./components/variables.js";

class Product extends Component {

    constructor(props) {
        super(props);

        this.state = {products:this.getProducts()};

        // console.log(this.props)
        console.log(this.state)
    }


    getProducts() {
            var fetchoptions = { method: 'GET'};

            fetch("http://localhost:8082/products/" + this.props.match.params.id, fetchoptions)
            .then(response => response.json())
            // .then(products => this.setState({products}))
            .then(function(myJson) {
                console.log(myJson)
                return myJson;
            });
            // }).catch();
    }


    //Testdata
    prod = [
        {
            productId: "5",
            name: "Corsair GS600 600 Watt PSU",
            description: "The Corsair Gaming Series GS600 is the ideal price/performance choice for mid-spec gaming PC",
            category: "Groente",
            price: 1234.00
        },
    ];

    saveProductToSession(prod) {
        // let title =  ;
        // let amount = document.getElementById('product-quantity').innerHTML;
        // alert(product);

    }

    render() {
        // var test;
        // if (!this.state.product) {
        //     console.log(this.state.products);
        //     // test = <div className="leeg"/>
        // } else {
        //     test = <div className="vol"/>
        // }

        const { test } = this.state;
        return (
            <div>
                <div className="container-fluid">
                    <div className="content-wrapper">
                        <div className="item-container">
                                <div className="container">
                                    <div className="col-md-6 product-page__container">
                                        <div className="product">

                                            {this.getProducts()}

                                            {test}
                                            {/*{this.state.products.map(product => (*/}
                                                {/*<div>{product.title}</div>*/}
                                            {/*))}*/}
                                            <img id="item-display" src={productimage} alt="product"/>
                                        </div>

                                        <div id="title" ref="title">test</div>
                                        <div className="product-desc"></div>
                                        <div className="col-md-3"></div>
                                        <div className="product-price">$ </div>
                                        <input type="number" className="quantity"/>

                                        {/* Foutmelding genereren op basis van voorraad */}

                                        <div className="product-stock">Op voorraad</div>
                                        <div className="btn-group cart">
                                            <button type="button" className="btn btn-success btn-cart" onClick={this.saveProductToSession()}>
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
            </div>
        );
    }
}

export default Product;
