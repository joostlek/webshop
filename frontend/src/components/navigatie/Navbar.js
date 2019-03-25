import React, { Component } from "react";
import logo from "../../assets/img/logo.png";
import { NavLink } from "react-router-dom";

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
              <NavLink className="nav-link" to="/cart">
                Winkelwagen
                <span className="badge badge-secondary">
                  {JSON.parse(sessionStorage["cart"]).length}
                </span>
              </NavLink>
            </li>
          </ul>
        </div>
        <div className="navbar-right">
          <NavLink className="nav-link" to="/cart">
            <i>Cart </i>
            <span className="badge badge-secondary">{JSON.parse(sessionStorage["cart"]).length}</span>
          </NavLink>
        </div>
      </nav>
    );
  }
}

export default Navbar;
