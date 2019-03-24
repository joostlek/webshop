import React, {Component} from "react";
import "../../App.css";
import "../product.css"
import "bootstrap/dist/css/bootstrap.css";
import productimage from "../../assets/product.jpg";

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
            },
            body: JSON.stringify({
                id: this.state.id
            })
        };

        fetch("http://localhost:8082/products/" + this.props.id, fetchoptions)
            .then(function(response) {
                if (response.ok) alert("Product deleted");
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

            // <div className="container-fluid">
            //     <div className="content-wrapper">
            //         <div className="item-container">
            //             <div className="container">
            //                 <div className="col-md-6 product-page__container">
            //                     <div className="">
            //                         <h1>Product toevoegen</h1>
            //                         <label>Voeg hier de foto toe</label><br />
            //                         <input className="addProduct" type="text"/>
            //                     </div>
            //                     <div>
            //                         <label>beschrijving</label><br />
            //                         <input className="addProduct" type="text" />
            //                     </div>
            //                     <div>
            //                         <label>prijs</label><br />
            //                         <input className="addProduct" type="text" />
            //                     </div>
            //                     <div className="btn-group cart">
            //                         <button type="button" className="btn btn-success btn-cart" onClick={()=>this.saveProductToSession()} >
            //                             Voeg product toe
            //                         </button>
            //                     </div>
            //                 </div>
            //             </div>
            //         </div>
            //     </div>
            // </div>
        );
    }
}

export default DeleteProduct;
