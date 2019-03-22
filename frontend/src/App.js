import React, {Component} from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import PrivateRoute from "react-private-route";

import Product from "./Product";
import ProductList from "./components/ProductList";
import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
import Cart from './components/Cart';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoggedIn: false
        };
        this.isLoggedIn = this.isLoggedIn.bind(this);
        this.toggleLogin = this.toggleLogin.bind(this);
    }

    isLoggedIn() {
        return this.state.isLoggedIn;
    }

    toggleLogin() {
        this.setState({isLoggedIn: !this.state.isLoggedIn});
    }

    render() {
        return (
            <BrowserRouter>
                <div className="container">
                    <div className="row">
                        <div className="col-12">
                            <Navbar/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-2">
                            <Sidebar/>
                        </div>
                        <div className="col-10">
                            <Switch>
                                <Route path="/" component={Product} exact/>
                                <Route path="/product" component={ProductList}/>
                                <PrivateRoute path="/cart" component={Cart} isAuthenticated={!!isLoggedIn()}/>
                                <Route path="/login"
                                       component={() => (<Login toggleLogin={this.toggleLogin} isLogged={this.state.isLoggedIn} />)}
                                />
                            </Switch>
                        </div>
                    </div>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
