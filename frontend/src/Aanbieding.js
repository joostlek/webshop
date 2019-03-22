import React, { Component } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import test from "./assets/test.svg";

class Product extends Component {
  //Testdata
  prod = [
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
      <div>
        <h1>Aanbiedingen van deze week</h1>
        <div className="row">
          {this.prod.map(prod => (
            <div className="col-4">
              <div className="card" style={{ width: 18 + "rem" }}>
                <img className="card-img-top" src={test} alt="Card image cap" />
                <div className="card-body">
                  <h5 className="card-title">{prod.Name}</h5>
                  <p className="card-text">{prod.Description}</p>
                  <a href="#" className="btn btn-primary">
                    Bekijk product
                  </a>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

export default Product;
