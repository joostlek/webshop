import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css";
import {Redirect} from "react-router-dom";

class Logout extends Component {

    constructor(props) {
        super(props);

        this.state = {
            redirect: false
        };
    }

    setRedirect() {
        this.setState({
            redirect: true
        });
    }

    renderRedirect() {
        if (this.state.redirect) {
            return <Redirect to='/' />
        }
    }

    logout() {
        sessionStorage.removeItem("myJWT");
    }

    render() {
        return(
            <div>
                {this.renderRedirect()}
                <button className="btn btn-primary" onClick={this.logout()}>Logout</button>
            </div>
        );
    }
}
export default Logout;