import React, { Component } from "react";

class Categorie extends Component {
  products = [
    {
      id: 1,
      title: "string",
      description: "string",
      price: 0,
      created: "2019-03-22T10:29:09.338+0000",
      updated: "2019-03-22T10:29:09.338+0000",
      category: 1
    },
    {
      id: 2,
      title: "paprika",
      description: "string",
      price: 50,
      created: "2019-03-22T10:40:21.457+0000",
      updated: "2019-03-22T10:40:21.457+0000",
      category: 6
    },
    {
      id: 3,
      title: "paprika",
      description: "string",
      price: 50,
      created: "2019-03-22T10:40:23.348+0000",
      updated: "2019-03-22T10:40:23.348+0000",
      category: 6
    },
    {
      id: 4,
      title: "paprika",
      description: "string",
      price: 50,
      created: "2019-03-22T10:42:57.707+0000",
      updated: "2019-03-22T10:42:57.707+0000",
      category: 6
    },
    {
      id: 5,
      title: "paprika",
      description: "string",
      price: 50,
      created: "2019-03-22T10:42:58.999+0000",
      updated: "2019-03-22T10:42:58.999+0000",
      category: 6
    },
    {
      id: 6,
      title: "paprika",
      description: "string",
      price: 50,
      created: "2019-03-22T10:43:00.785+0000",
      updated: "2019-03-22T10:43:00.785+0000",
      category: 6
    },
    {
      id: 7,
      title: "test",
      description: "string",
      price: 3,
      created: "2019-03-22T11:21:37.866+0000",
      updated: "2019-03-22T11:21:37.866+0000",
      category: 1
    }
  ];

  render() {
    return (
      <div>
        <h3>Categorie: "placeholder"</h3>
        <ul>
          {this.products.map(product => (
            <li className="list-group-item">{product.id}</li>
          ))}
        </ul>
      </div>
    );
  }
}

export default Categorie;
