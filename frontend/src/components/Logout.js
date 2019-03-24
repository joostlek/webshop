import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css";

class Logout extends Component {

    constructor(props) {
        super(props);
    }

    logout() {
        sessionStorage.removeItem("myJWT");
        sessionStorage.removeItem("cart");
        this.props.updatePage();
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