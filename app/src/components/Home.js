import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import React, { Component } from 'react';

import LoginForm from './LoginForm';
import PrivateRoute from './common/PrivateRoute';
import Dashboard from './Dashboard';
import RegisterForm from './RegisterForm';

import { ACCESS_TOKEN } from '../constants';

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isUserAuthenticated: false,
            customUser: null
        }

        this.isUserLoggedIn = this.isUserLoggedIn.bind(this);
    }

    isUserLoggedIn() {
        const accessToken = localStorage.getItem(ACCESS_TOKEN);
        if (accessToken) {
            this.setState({
                isUserAuthenticated: true
            });
            return true;
        }
        return false;
    }

    render() {
        return (
            <Router>
                <Switch>
                    <Route path="/login" exact component={LoginForm} />
                    <Route path="/register" exact component={RegisterForm} />
                    <PrivateRoute path="/" loggedIn={this.isUserLoggedIn} component={Dashboard} />
                </Switch>
            </Router>
        );
    }
}

export default Home;