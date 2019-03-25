import React, {Component} from "react";
import "../../assets/css/App.css";
import "../../assets/css/product.css";
import "bootstrap/dist/css/bootstrap.css";
import productimage from "../../assets/img/product.jpg";

class GetProduct extends Component {

    constructor(props) {
        super(props);

        console.log(this.props)

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

        let currentPrice;
        if (this.state.data.discount) {
            currentPrice = this.state.data.discount.discount;
        } else {
            currentPrice = this.state.data.price;
        }

        let product = {
            id: this.state.data.id,
            name: this.state.data.title,
            price: currentPrice,
            amount: this.state.amount
        };

        cart.push(product);

        cart = JSON.stringify(cart);

        sessionStorage["cart"] = cart;
    }


    isAdmin() {
        if(!sessionStorage["myJWT"]) {
            return false
        }

        let jwtData = sessionStorage["myJWT"].split(".")[1];
        let jwtRoleData = JSON.parse( window.atob(jwtData) ).auth;

        for (let i=0; i<jwtRoleData.length; i++) {
            if (jwtRoleData[i].authority === "ROLE_ADMIN") {
                return true;
            }
        }
        return false;
    }


    render() {
        let test;

        let deleteButton = <div />;

        if (this.isAdmin()) {
            deleteButton = (
                <button type="button" className="btn btn-danger">
                    Verwijder product van de catalogus
                </button>
            );
        }

        let priceComponent;
        let timeComponent;

        if (this.state.data.discount) {
            priceComponent = (
                <div className="product-price">
                    <div className="price-was">Van {this.state.data.price}</div>
                    <div className="price-now">€{this.state.data.discount.discount}</div>
                </div>
            );
            timeComponent = (
                <div className="product-price">
                    <label>Deze aanbieding loopt van</label>
                    <div className="">{this.state.data.discount.begin_date} tot</div>
                    <div className="">{this.state.data.discount.end_date}</div>
                </div>
            );
        } else {
            priceComponent = (
                <div className="product-price">
                    €{this.state.data.price}
                </div>
            );
            timeComponent = null;
        }

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
                                    {priceComponent}
                                    {timeComponent}
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
                                    <div className="btn-group delete">
                                        {deleteButton}
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

export default GetProduct;
