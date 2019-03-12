import React, { Component } from 'react';
import './App.css';
import Product from "./Product";
import 'bootstrap/dist/css/bootstrap.css';


class App extends Component {
  render() {
    return (
      <div className="container">
        <Product></Product>
      </div>
    );
  }
}

export default App;
