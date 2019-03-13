import React, { Component } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import test from "./assets/test.svg";

class Product extends Component {
  //Testdata
  prod = [
    {
      ProductId: "HT-1000",
      Category: "Laptops",
      Description:
        'Notebook Basic 15 with 2,80 GHz quad core, 15" LCD, 4 GB DDR3 RAM, 500 GB Hard Disc, Windows 8 Pro',
      Name: "Notebook Basic 15",
      DateOfSale: "2017-03-26",
      ProductPicUrl: test,
      Status: "Available",
      Quantity: 10
    },
    {
      ProductId: "HT-1000",
      Category: "Laptops",
      Description:
        'Notebook Basic 15 with 2,80 GHz quad core, 15" LCD, 4 GB DDR3 RAM, 500 GB Hard Disc, Windows 8 Pro',
      Name: "Notebook Basic 3000",
      DateOfSale: "2017-03-26",
      ProductPicUrl: test,
      Status: "Available",
      Quantity: 10
    }
  ];

  render() {
    return (
      <div>
        {this.prod.map(prod => (
          <div className="card" style={{ width: 18 + "rem" }}>
            <img
              className="card-img-top"
              src={prod.ProductPicUrl}
              alt="Card image cap"
            />
            <div className="card-body">
              <h5 className="card-title">{prod.Name}</h5>
              <p className="card-text">{prod.Description}</p>
              <a href="#" className="btn btn-primary">
                Bekijk product
              </a>
            </div>
          </div>
        ))}
      </div>
    );
  }
}

export default Product;
