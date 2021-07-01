import React, { Component } from 'react'
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import SideBar from './SideBar';
import UploadExpenses from './UploadExpenses';
import ExpensesOverview from './ExpensesOverview';
import { history } from './common/History';

import { ACCESS_TOKEN } from "../constants";


class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {}

        this.handleLogout = this.handleLogout.bind(this);
    }

    handleLogout() {
        localStorage.removeItem(ACCESS_TOKEN);
        this.props.history.push("/");
    }

    render() {
        return (
            <Router history={history}>
                <div class="relative min-h-screen md:flex">
                    <SideBar onLogout={this.handleLogout}/>

                    <Switch>
                        <Route path="/uploadExpenses" exact component={UploadExpenses} />
                        <Route path="/overview" exact component={ExpensesOverview} />
                    </Switch>
                </div>
            </Router>
        );
    }
}

export default Dashboard;