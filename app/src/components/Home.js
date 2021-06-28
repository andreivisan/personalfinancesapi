import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import React, { Component } from 'react';
import { withRouter } from 'react-router';
import { Redirect } from 'react-router-dom';

import LoginForm from './LoginForm';
import PrivateRoute from './common/PrivateRoute';
import Dashboard from './Dashboard';
import RegisterForm from './RegisterForm';
import { history } from './common/History';


import { ACCESS_TOKEN } from '../constants';

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isUserAuthenticated: false,
            customUser: null
        }

        this.loadUser = this.loadUser.bind(this);
    }
    
    componentDidMount() {
        this.loadUser();
    }

    loadUser() {
        if (localStorage.getItem(ACCESS_TOKEN)) {
            this.setState({
                isUserAuthenticated: true
            });
        }
    }

    render() {
        return (
            <Router history={history}>
                <Switch>
                    <Route path="/login" exact component={LoginForm} />
                    <Route path="/register" exact component={RegisterForm} />
                    <PrivateRoute exact path="/" loggedIn={this.state.isUserAuthenticated} component={(props) => <Dashboard {...props} />} />
                </Switch>
            </Router>
        );
    }
}

export default withRouter(Home);