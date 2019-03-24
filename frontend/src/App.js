import React, {Component} from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import ProductList from "./components/ProductList";
import Product from "./components/Product";
import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
import Cart from "./components/Cart";
import Categorie from "./Categorie";
import Dashboard from "./Dashboard";
import AddProduct from "./components/product/AddProduct";

class App extends Component {

  constructor(props) {
    super(props);

    sessionStorage["cart"] = "[]";
  }

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
                <Route path="/addproduct" component={AddProduct} />
              </Switch>
            </div>
          </div>
        </div>
      </Router>
    );
  }
}

export default App;
