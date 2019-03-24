import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.css";
import "../../assets/css/App.css";
import { BrowserRouter as Router, NavLink } from "react-router-dom";

class Categorie extends Component {
  constructor(props) {
    super(props);

    this.state = { products: [] };
  }

  //gets all products from api and put them in this.state
  componentDidMount() {
    var fetchoptions = { method: "GET" };
    fetch("http://localhost:8082/products/", fetchoptions)
      .then(response => {
        return response.json();
      })
      .then(response => {
        this.setState({ products: response });
      });
  }

  render() {
    return (
      <div>
        <h3>Categorie</h3>
        <p>Deze categorie bevat de volgende producten:</p>
        <ul>
          {this.state.products.map(product =>
            this.props.match.params.id === product.category.id ? (
              <li className="list-group-item" key={product.id}>
                <label id="productp">{product.title}</label>
                <button className="btn btn-outline-info">
                  <NavLink to={"/product/" + product.id}>
                    Bekijk product
                  </NavLink>
                </button>
              </li>
            ) : null
          )}
        </ul>
      </div>
    );
  }
}

export default Categorie;
