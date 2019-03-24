import React, {Component} from "react";
import "../App.css";
import "./product.css"
import "bootstrap/dist/css/bootstrap.css";
import productimage from "../assets/product.jpg";
// import "./components/variables.js";

class Product extends Component {

    constructor(props) {
        super(props);

        this.state = { data: [] }
        // this.state = {products:null};

        // this.getProducts();

    }

    componentDidMount () {
        console.log("Component did mount")
        var fetchoptions = {method: 'GET'};
        fetch("http://localhost:8082/products/" + this.props.match.params.id, fetchoptions)
            .then((response) => {
                return response.json();
            })
            .then( (response) => {
                console.log(response)
                this.setState({data: response});
            })
    }

    // async getProducts() {
    //
    //     try {
    //         var fetchoptions = {method: 'GET'};
    //         const response = fetch("http://localhost:8082/products/" + this.props.match.params.id, fetchoptions)
    //             .then(response => this.setState({ data: response.data}));
    //         // let responseBody = response.json();
    //         // await this.setState({products: responseBody});
    //         // console.log(responseBody);
    //         // this.forceUpdate();
    //     } catch(error) {
    //         console.log(error);
    //     }
    // }


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

    saveProductToSession(prod){

    }

    render() {
        let test;
        if (!this.state.data) {
            test = (<div></div>);
        } else {
            test = (

                    <div className="container-fluid">
                        <div className="content-wrapper">
                            <div className="item-container">
                                <div className="container">
                                    <div className="col-md-6 product-page__container">
                                        <div className="product">
                                            {this.state.data}

                                            {/*{this.getProducts()}*/}

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
                                        <p>Aantal</p>
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
