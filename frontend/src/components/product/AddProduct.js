import React, {Component} from "react";
import "../../App.css";
import "../product.css"
import "bootstrap/dist/css/bootstrap.css";
import productimage from "../../assets/product.jpg";

class AddProduct extends Component {

    constructor(props) {
        super(props);

        this.state = {
            title: '',
            description: '',
            price: ''
        }
    }

    save() {
        let fetchoptions = {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                title: this.state.title,
                description: this.state.description,
                price: this.state.description
            })
        };

        fetch("http://localhost:8082/users/signin", fetchoptions)
            .then(function(response) {
                if (response.ok) return response.json();
                else throw "Wrong username/password";
            })
            .then((response) => {
                sessionStorage["myJWT"] = response.token;
                this.props.updatePage();
            })
            .catch(function(error) {
                console.log(error);
            });
    }

    render() {
        return (
            <div className="container-fluid">
                <div className="content-wrapper">
                    <div className="item-container">
                        <div className="container">
                            <div className="col-md-6 product-page__container">
                                <div className="">
                                    <h1>Product toevoegen</h1>
                                    <label>Voeg hier de foto toe</label><br />
                                    <input className="addProduct" type="text"/>
                                </div>
                                <div>
                                    <label>beschrijving</label><br />
                                    <input className="addProduct" type="text" />
                                </div>
                                <div>
                                    <label>prijs</label><br />
                                    <input className="addProduct" type="text" />
                                </div>
                                <div className="btn-group cart">
                                    <button type="button" className="btn btn-success btn-cart" onClick={()=>this.saveProductToSession()} >
                                        Voeg product toe
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddProduct;
