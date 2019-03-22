import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css";

class Login extends Component {

    login() {

        this.toggleLogin();
    }

    logout() {

    }

    render() {
        let component;
        if (this.props.isLoggedIn) {
            component = (
                <input
                <button onClick={this.login()}>Login</button>
            );
        } else {
            component = (
                <button onClick={this.logout()}>Logout</button>
            );
        }


        return(
            <div>
                {component};
            </div>
        );
    }
}
export default Login;