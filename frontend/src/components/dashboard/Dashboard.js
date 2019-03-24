import React, { Component } from "react";
import "../../assets/css/App.css";
import "bootstrap/dist/css/bootstrap.css";
import test from "../../assets/img/test.svg";

import { NavLink } from 'react-router-dom';

class Dashboard extends Component {
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

  constructor(props) {
    super(props);

    this.state = {
      products: []
    };
  }

  componentDidMount() {
    var fetchoptions = {method: 'GET'};
    fetch("http://localhost:8082/products/", fetchoptions)
        .then((response) => {
          return response.json();
        })
        .then( (response) => {
          this.setState({products: this.filterProducts(response)});
        })
        .then(() => {
          this.forceUpdate();
        });
  }

  filterProducts(array) {
    let products = array;

    let discountedProducts = [];

    for (let i=0; i<products.length; i++) {
      if (products[i].discount) {
        discountedProducts.push(products[i]);
      }
    }

    return discountedProducts;

  }

  render() {
    return (
      <div>
        <h1>Aanbiedingen van deze week</h1>
        <div className="row">
          {this.state.products.map(prod => (
            <div className="col-4" key={prod.id}>
              <div className="card" style={{ width: 18 + "rem" }}>
                <img className="card-img-top" src={test} alt="Placeholder" />
                <div className="card-body">
                  <h5 className="card-title">{prod.title}</h5>
                  <p className="card-text">{prod.description}</p>
                  <NavLink to={"/product/"+prod.id}>Bekijk Product</NavLink>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

export default Dashboard;
