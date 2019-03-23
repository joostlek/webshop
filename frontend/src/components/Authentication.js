import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css";
import Login from "./Login";
import Logout from "./Logout";

class Authentication extends Component {

    constructor(props) {
        super(props);

    }

    render() {
        let component;
        if (sessionStorage["myJWT"]) {
            component = <Logout />;
        } else {
            component = <Login />;
        }

        console.log(component);

        return(
            <div>{component}</div>
        );
    }
}
export default Authentication;