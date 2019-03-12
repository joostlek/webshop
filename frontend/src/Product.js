import React, { Component } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';


class Product extends Component {

    prod = {
        ProductId: "HT-1000",
        Category: "Laptops",
        Description: "Notebook Basic 15 with 2,80 GHz quad core, 15\" LCD, 4 GB DDR3 RAM, 500 GB Hard Disc, Windows 8 Pro",
        Name: "Notebook Basic 15",
        DateOfSale: "2017-03-26",
        ProductPicUrl: "test-resources/sap/ui/demokit/explored/img/HT-1000.jpg",
        Status: "Available",
        Quantity: 10,
    };

    render() {
        // return (
            {/*<div>{this.test.tags.map(tag => <li>{tag}</li> )}</div>*/}
        const productKey = Object.entries(this.prod).map(([key,value])=>{
            return (
                <div>key</div>
            )
        })

        const productValue = Object.entries(this.prod).map(([key,value])=>{
            return (
                <div>{value.toString()}</div>
            )
        })

        return(
            <div className="card" style={{width: 18 + 'rem'}}>
                <img className="card-img-top" src="..." alt="Card image cap"></img>
                    <div className="card-body">
                        <h5 className="card-title">{productValue[3]}</h5>
                        <p className="card-text">{productValue[2]}</p>
                        <a href="#" className="btn btn-primary">Go somewhere</a>
                    </div>
            </div>
        );
        // );
    }
}

export default Product;
