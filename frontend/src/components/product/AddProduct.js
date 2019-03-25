import React, {Component} from "react";
import "../../assets/css/App.css";
import "../../assets/css/product.css";
import "bootstrap/dist/css/bootstrap.css";

class AddProduct extends Component {

    constructor(props) {
        super(props);

        this.state = {
            title: '',
            description: '',
            price: 0
        }

        // Bind handleChanges
        this.handleTitleChange = this.handleTitleChange.bind(this);
        this.handleDescriptionChange = this.handleDescriptionChange.bind(this);
        this.handlePriceChange = this.handlePriceChange.bind(this);

    }

    handleTitleChange(event) {
        this.setState({
            title: event.target.value
        });
    }

    handleDescriptionChange(event) {
        this.setState({
            description: event.target.value
        });
    }

    handlePriceChange(event) {
        this.setState({
            price: event.target.value
        });
    }

    save() {
        let fetchoptions = {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '+ sessionStorage["myJWT"],
            },
            body: JSON.stringify({
                title: this.state.title,
                description: this.state.description,
                price: this.state.price
            })
        };

        fetch("http://localhost:8082/products", fetchoptions)
            .then(function(response) {
                if (response.ok) return response.json();
                else throw "Er is iets fout gegaan met het toevoegen van het product.";
            })
            .then((response) => {
                alert(response.title + 'is toegevoegd')
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
                                    <input className="addProduct" type="text" />
                                </div>
                                <div>
                                    <label>titel</label><br />
                                    <input className="addProduct" type="text" value={this.state.title} onChange={this.handleTitleChange} />
                                </div>
                                <div>
                                    <label>beschrijving</label><br />
                                    <input className="addProduct" type="text" value={this.state.description} onChange={this.handleDescriptionChange} />
                                </div>
                                <div>
                                    <label>prijs</label><br />
                                    <input className="addProduct" type="number" value={this.state.price} onChange={this.handlePriceChange} />
                                </div>
                                <div className="btn-group cart">
                                    <button type="button" className="btn btn-success btn-cart" onClick={()=>this.save()} >
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
