import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css";
import Login from "./Login";
import Logout from "./Logout";

class Authentication extends Component {

    constructor(props) {
        super(props);

        this.updatePage = this.updatePage.bind(this);
    }

    updatePage() {
        this.forceUpdate();
    }

    render() {
        let component;
        if (sessionStorage["myJWT"]) {
            component = <Logout updatePage={this.updatePage} />;
        } else {
            component = <Login updatePage={this.updatePage} />;
        }

        console.log(component);

        return(
            <div>{component}</div>
        );
    }
}
export default Authentication;