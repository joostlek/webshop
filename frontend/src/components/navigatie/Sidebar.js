import React, { Component } from "react";
import { NavLink } from "react-router-dom";


export class Sidebar extends Component {
  constructor(props) {
    super(props);

    this.state = { categories: [] };
  }

  //gets all categories from api and put them in this.state
  componentDidMount() {
    var fetchoptions = { method: "GET" };
    fetch("http://localhost:8082/categories/", fetchoptions)
      .then(response => {
        return response.json();
      })
      .then( (response) => {
        this.setState({categories: response});
      })
  }

  render() {
    return (
      <div>
        <ul>
          <li className="list-group-item active">Categoriën</li>
          {this.state.categories.map(category => (
            <li className="list-group-item" key={category.id}>
              <NavLink to={"/categorie/" + category.id}>{category.name}</NavLink>
            </li>
          ))}
        </ul>
      </div>
    );
  }
}

export default Sidebar;
