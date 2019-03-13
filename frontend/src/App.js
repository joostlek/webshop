import React, { Component } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import logo from "./assets/logo.png";
import Product from "./Product";
import Navbar from "./Navbar";

class App extends Component {
  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col-2">
            <img src={logo} />
          </div>
          <div className="col-10">
            <Navbar />
          </div>
        </div>

        <div className="row">
          <div className="col-2" />
          <div className="col-10">
            <Product />
          </div>
        </div>
      </div>
    );
  }
}

export default App;
