import React, {Component} from "react";
import "../../assets/css/App.css";
import "../../assets/css/product.css";
import "bootstrap/dist/css/bootstrap.css";
import productimage from "../../assets/img/product.jpg";

class DeleteProduct extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: null
        }
    }

    delete() {
        let fetchoptions = {
            method: "DELETE",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '+ sessionStorage["myJWT"],

            },
            body: JSON.stringify({
                id: this.state.id
            })
        };

        fetch("http://localhost:8082/products/" + this.props.id, fetchoptions)
            .then(function(response) {
                if (response.ok) alert("GetProduct deleted");
            })
            .catch(function(error) {
                console.log(error);
            });
    }

    render() {
        return (
            <div>
                <button onClick={() => this.delete()}>Delete</button>
            </div>
        );
    }
}

export default DeleteProduct;