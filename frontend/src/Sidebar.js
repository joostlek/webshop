import React, { Component } from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import Categorie from "./Categorie";

const categories = [
  {
    id: 1,
    naam: "Fruit"
  },
  {
    id: 2,
    naam: "Groente"
  },
  {
    id: 3,
    naam: "Zuivel"
  },
  {
    id: 4,
    naam: "Frisdrank"
  },
  {
    id: 5,
    naam: "Vlees"
  },
  {
    id: 6,
    naam: "Snack"
  }
];

export class Sidebar extends Component {
  render() {
    return (
      <Router>
        <div>
          <ul>
            <li className="list-group-item active">Categoriën</li>
            {categories.map(({ id, naam }) => (
              <li className="list-group-item" key={id}>
                <Link to={"/categorie/" + id}>{naam}</Link>
              </li>
            ))}
          </ul>
        </div>
      </Router>
    );
  }
}

export default Sidebar;
