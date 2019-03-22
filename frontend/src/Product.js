import React, {Component} from "react";
import "./App.css";
import "./product.css"
import "bootstrap/dist/css/bootstrap.css";
import productimage from "./assets/product.jpg";
// import "./components/variables.js";

class Product extends Component {

    getProducts() {
        fetch("127.0.0.1")
            .then(result=>result.json())
            .then(items=>this.setState({items}))
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

    }

    render() {
        return (
            <div>
                <div className="container-fluid">
                    <div className="content-wrapper">
                        <div className="item-container">
                            {this.prod.map(prod => (
                                <div className="container">
                                    <div className="col-md-6 product-page__container">
                                        <div className="product">
                                            <img id="item-display" src={productimage}/>
                                        </div>

                                        <div className="product-title">{prod.Name}</div>
                                        <div className="product-desc">{prod.Description}</div>
                                        <div className="col-md-3"></div>
                                        <div className="product-price">$ {prod.Price}</div>

                                        {/* Foutmelding genereren op basis van voorraad */}

                                        <div className="product-stock">Op voorraad</div>
                                        <div className="btn-group cart">
                                            <button type="button" className="btn btn-success btn-cart">
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
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Product;