import React, { Component } from "react";

export class Sidebar extends Component {
  render() {
    return (
      <div>
        <ul className="list-group">
          <li className="list-group-item active">Producten</li>
          <li className="list-group-item">Aanbiedingen</li>
          <li className="list-group-item">Categoriën</li>
        </ul>
      </div>
    );
  }
}

export default Sidebar;
