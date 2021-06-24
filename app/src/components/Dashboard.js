import React, { Component } from 'react'
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import SideBar from './SideBar';
import UploadExpenses from './UploadExpenses';
import ExpensesOverview from './ExpensesOverview';
import PrivateRoute from './common/PrivateRoute';
import LoginForm from './LoginForm';
import RegisterForm from './RegisterForm';

class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }
    render() {
        return (
            <Router>
                <div class="relative min-h-screen md:flex">
                    <SideBar />

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