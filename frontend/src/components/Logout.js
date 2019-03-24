import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css";
import {Redirect} from "react-router-dom";

class Logout extends Component {

    logout() {
        sessionStorage.removeItem("myJWT");
        this.setRedirect();
    }

    render() {
        return(
            <div>
                <button className="btn btn-primary" onClick={() => this.logout()}>Logout</button>
            </div>
        );
    }
}
export default Logout;