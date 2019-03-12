import React, {Component} from 'react';
import './App.css';

class Product extends Component {

    prod = {
        ProductId: "HT-1000",
        Category: "Laptops",
        MainCategory: "Computer Systems",
        TaxTarifCode: "1",
        SupplierName: "Very Best Screens",
        WeightMeasure: 4.2,
        WeightUnit: "KG",
        Description: "Notebook Basic 15 with 2,80 GHz quad core, 15\" LCD, 4 GB DDR3 RAM, 500 GB Hard Disc, Windows 8 Pro",
        Name: "Notebook Basic 15",
        DateOfSale: "2017-03-26",
        ProductPicUrl: "test-resources/sap/ui/demokit/explored/img/HT-1000.jpg",
        Status: "Available",
        Quantity: 10,

    };

    test = {
        tags: [' test', 'test2']
    }

    render() {
        // return (
            {/*<div>{this.test.tags.map(tag => <li>{tag}</li> )}</div>*/}
        const haha = Object.entries(this.prod).map(([key, value]) => {
            return (
                <div>{key} : {value.toString()}</div>
            )
        })

        return (Object.entries(haha)[1]);
        // );
    }
}

export default Product;
