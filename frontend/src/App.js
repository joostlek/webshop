import React, { Component } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";

import { BrowserRouter, Route } from "react-router-dom";

import Product from "./Product";
// import ProductList from "./components/ProductList";
import Navbar from "./Navbar";
import Sidebar from "./Sidebar";

class App extends Component {
  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col-12">
            <Navbar />
          </div>
        </div>

        <div className="row">
          <div className="col-2">
            <Sidebar />
          </div>
          <div className="col-10">
            <Product />
          </div>
        </div>
      </div>
    );
  }
}

export default App;
