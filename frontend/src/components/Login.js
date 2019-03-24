import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.css";

class Login extends Component {

    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: '',
        };

        this.handleUsernameChange = this.handleUsernameChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);

    }

    handleUsernameChange(event) {
        this.setState({
            username: event.target.value
        });
    }

    handlePasswordChange(event) {
        this.setState({
            password: event.target.value
        });
    }

    login() {
        let fetchoptions = {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: this.state.username,
                password: this.state.password
            })
        };

        fetch("http://localhost:8082/users/signin", fetchoptions)
            .then(function(response) {
                if (response.ok) return response.json();
                else throw "Wrong username/password";
            })
            .then((response) => {
                sessionStorage["myJWT"] = response.token;
                this.props.updatePage();
            })
            .catch(function(error) {
                console.log(error);
            });
    }

    render() {
        return(
            <div>
                <label className="row">
                    <span className="col-md-2">Username:</span>
                    <input className="col-md-3" type="text" value={this.state.username} onChange={this.handleUsernameChange}/>
                </label>
                <label className="row">
                    <span className="col-md-2">Password:</span>
                    <input className="col-md-3" type="password" value={this.state.password} onChange={this.handlePasswordChange}/>
                </label>
                <button className="btn btn-primary" onClick={() => this.login()}>Login</button>
            </div>
        );
    }
}
export default Login;