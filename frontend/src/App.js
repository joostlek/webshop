import React, { Component } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import Product from "./Product";
import Dashboard from "./Dashboard";
import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
import Cart from "./components/Cart";
import Categorie from "./Categorie";

class App extends Component {
  render() {
    return (
      <Router>
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
              <Switch>
                <Route path="/" component={Dashboard} exact />
                <Route path="/product/:id" component={Product} />
                <Route path="/cart" component={Cart} />
                <Route path="/categorie/:id" component={Categorie} />
              </Switch>
            </div>
          </div>
        </div>
      </Router>
    );
  }
}

export default App;
