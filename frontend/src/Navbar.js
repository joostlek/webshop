import React, { Component } from "react";
import logo from "./assets/logo.png";
import { NavLink } from "react-router-dom";
import Cart from "./components/Cart";

export class Navbar extends Component {
  render() {
    return (
      <nav
        className="navbar navbar-expand-lg navbar-light"
        style={{ backgroundColor: "#FFFFFF" }}
      >
        <a className="navbar-brand">
          <img src={logo} alt="Logo" />
        </a>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item">
              <NavLink className="nav-link" to="/">
                Dashboard <span className="sr-only">(current)</span>
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/product">
                Products
              </NavLink>
            </li>
          </ul>
        </div>
        <div className="navbar-right">
          <Cart />
        </div>
      </nav>
    );
  }
}

export default Navbar;
