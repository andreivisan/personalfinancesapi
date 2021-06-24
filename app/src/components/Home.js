import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import React, { Component } from 'react';

import LoginForm from './LoginForm';
import PrivateRoute from './common/PrivateRoute';
import Dashboard from './Dashboard';
import RegisterForm from './RegisterForm';

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isUserAuthenticated: false,
            customUser: null
        }
    }

    render() {
        return (
            <Router>
                <Switch>
                    <Route path="/login" exact component={LoginForm} />
                    <Route path="/register" exact component={RegisterForm} />
                    <PrivateRoute path="/" loggedIn={this.state.isUserAuthenticated} component={Dashboard} />
                </Switch>
            </Router>
        );
    }
}

export default Home;