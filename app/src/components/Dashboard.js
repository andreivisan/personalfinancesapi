import React, { Component } from 'react'
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import SideBar from './SideBar';
import UploadExpenses from './UploadExpenses';
import ExpensesOverview from './ExpensesOverview';
import { history } from './common/History';
import axios from 'axios';

import { ACCESS_TOKEN } from "../constants";


class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: {}
        }

        this.handleLogout = this.handleLogout.bind(this);
        this.loadCurrentUser = this.loadCurrentUser.bind(this);
    }

    loadCurrentUser() {
        const jwtToken = localStorage.getItem(ACCESS_TOKEN);
        axios.get('api/v1/clients/me', {
            headers: {
                'Authorization': jwtToken
            }
        })
            .then(response => {
                this.setState({ currentUser: response.data });
            })
            .catch(function (error) {
                console.log(error);
            })
    }
    
    componentDidMount() {
        this.loadCurrentUser();
    }
 
    handleLogout() {
        localStorage.removeItem(ACCESS_TOKEN);
        this.props.history.push("/");
    }

    render() {
        return (
            <Router history={history}>
                <div class="relative min-h-screen md:flex">
                    <SideBar onLogout={this.handleLogout} currentUser={this.state.currentUser} />

                    <Switch>
                        <Route path="/uploadExpenses" render={() => <UploadExpenses currentUser={this.state.currentUser} />} />
                        <Route path="/overview" render={() => <ExpensesOverview currentUser={this.state.currentUser} />} />
                    </Switch>
                </div>
            </Router>
        );
    }
}

export default Dashboard;