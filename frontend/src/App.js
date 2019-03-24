import React, {Component} from "react";
import "../../assets/css/App.css";
import "bootstrap/dist/css/bootstrap.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import GetProduct from "./components/product/GetProduct";
import Navbar from "./components/navigatie/Navbar";
import Sidebar from "./components/navigatie/Sidebar";
import Cart from "./components/cart/Cart";
import Categorie from "./components/categorie/Categorie";
import Dashboard from "./components/dashboard/Dashboard";
import Authentication from "./components/authentication/Authentication";
import AddProduct from "./components/product/AddProduct";
import UpdateProduct from "./components/product/UpdateProduct";

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
                <Route path="/product/:id" component={GetProduct} />
                <Route path="/cart" component={Cart} />
                <Route path="/categorie/:id" co mponent={Categorie} />
                <Route path="/login" component={Authentication}/>
                <Route path="/addproduct" component={AddProduct} />
                <Route path="/updateproduct/:id" component={UpdateProduct} />
              </Switch>
            </div>
          </div>
        </div>
      </Router>
    );
  }
}

export default App;
