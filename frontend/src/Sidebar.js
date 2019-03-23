import React, { Component } from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

export class Sidebar extends Component {
  render() {
    return (
      <Router>
        <div>
          <ul className="list-group">
            <li className="list-group-item ">Categoriën</li>
            <li className="list-group-item">
              <Link to="/categorie">Fruit</Link>
            </li>
            <li className="list-group-item">
              <Link to="/categorie">Groente</Link>
            </li>
            <li className="list-group-item">
              <Link to="/categorie">Zuivel</Link>
            </li>
            <li className="list-group-item">
              <Link to="/categorie">Vlees</Link>
            </li>
            <li className="list-group-item">
              <Link to="/categorie">Frisdrank</Link>
            </li>
          </ul>
        </div>
      </Router>
    );
  }
}

const Child = ({ match }) => <div>{match.params.id}</div>;

export default Sidebar;
