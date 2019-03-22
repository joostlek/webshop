import React, {Component} from "react";
import "./App.css";
import "./product.css"
import "bootstrap/dist/css/bootstrap.css";
import productimage from "./assets/product.jpg";

class Product extends Component {

    //Testdata
    prod = [
        {
            ProductId: "5",
            Name: "Corsair GS600 600 Watt PSU",
            Description: "The Corsair Gaming Series GS600 is the ideal price/performance choice for mid-spec gaming PC",
            Category: "Groente",
            Price: 1234.00
        },
    ];

    saveProductToSession(prod) {
        for (let i=-0; i < prod.length;i++) {

        }


        let session = window.sessionStorage;
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
                                        <div className="product-rating"><i className="fa fa-star gold"></i> <i
                                            className="fa fa-star gold"></i> <i className="fa fa-star gold"></i> <i
                                            className="fa fa-star gold"></i> <i className="fa fa-star-o"></i></div>
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
