import React, { Component } from "react";
// import {BrowserRouter, Route, Switch} from "react-router-dom";

class Categorie extends Component {
  categorie = [
    {
      id: 1,
      name: "fruit"
    },
    {
      id: 2,
      name: "snack"
    }
  ];

  render() {
    console.log(this.props);
    return (
      <div>
        <h1>Categorie</h1>
      </div>
    );
  }
}

export default Categorie;
