import React, {Component} from "react";
import "./assets/css/App.css";
import "bootstrap/dist/css/bootstrap.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import PrivateRoute from "react-private-route";

import GetProduct from "./components/product/GetProduct";
import Navbar from "./components/navigatie/Navbar";
import Sidebar from "./components/navigatie/Sidebar";
import Cart from "./components/cart/Cart";
import Categorie from "./components/categorie/Categorie";
import Dashboard from "./components/dashboard/Dashboard";
import Authentication from "./components/authentication/Authentication";
import AddProduct from "./components/product/AddProduct";
import UpdateProduct from "./components/product/UpdateProduct";
import Afrekenen from "./components/afrekenen/Afrekenen";

class App extends Component {

  constructor(props) {
    super(props);
    if(!sessionStorage["cart"]){
      sessionStorage["cart"] = "[]";
    }
  }

  isAdmin() {
    if(!sessionStorage["myJWT"]) {
      return false
    }

    let jwtData = sessionStorage["myJWT"].split(".")[1];
    let jwtRoleData = JSON.parse( window.atob(jwtData) ).auth;

    for (let i=0; i<jwtRoleData.length; i++) {
      if (jwtRoleData[i].authority === "ROLE_ADMIN") {
        return true;
      }
    }
    return false;
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
                <PrivateRoute path="/addproduct" component={AddProduct} isAuthenticated={this.isAdmin()} />
                <PrivateRoute path="/updateproduct/:id" component={UpdateProduct} isAuthenticated={this.isAdmin()} />
                <Route path="/afrekenen" component={Afrekenen} />
              </Switch>
            </div>
          </div>
        </div>
      </Router>
    );
  }
}

export default App;
