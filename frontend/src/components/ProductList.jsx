import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.css";
import test from "./test.svg";

export class ProductList extends Component {
  products = [
    {
      ProductId: "1",
      Name: "Appel",
      Category: "Fruit",
      Price: 5
    },
    {
      ProductId: "2",
      Name: "Peer",
      Category: "Fruit",
      Price: 3
    },
    {
      ProductId: "3",
      Name: "Avacado",
      Category: "Fruit",
      Price: 6
    },
    {
      ProductId: "4",
      Name: "Bloemkool",
      Category: "Groente",
      Price: 2
    },
    {
      ProductId: "5",
      Name: "Komkommer",
      Category: "Groente",
      Price: 1
    },
    {
      ProductId: "6",
      Name: "Chips",
      Category: "Snack",
      Price: 1.5
    }
  ];

  render() {
    return (
      <div className="row">
        {this.products.map(product => (
          <div className="col-4" key={product.ProductId}>
            <div className="card" style={{ width: 18 + "rem" }}>
              <img className="card-img-top" src={test} alt="Card image cap" />
              <div className="card-body">
                <h5 className="card-title">{product.Name}</h5>
                <p className="card-text">
                  Categorie: {product.Category}
                  <br /> Prijs: €{product.Price}
                </p>
                <a href="#" className="btn btn-primary">
                  Bekijk product
                </a>
              </div>
            </div>
          </div>
        ))}
      </div>
    );
  }
}

export default ProductList;
