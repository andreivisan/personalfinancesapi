import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import React, { Component } from 'react';

import SideBar from './SideBar';
import UploadExpenses from './UploadExpenses';
import Dashboard from './Dashboard';

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }

    render() { 
        return ( 
            <div class="relative min-h-screen md:flex">
                <Router>
                    <SideBar />

                    <Switch>
                        <Route path="/uploadExpenses" component={UploadExpenses} />
                        <Route path="/dashboard" component={Dashboard} />
                    </Switch>
                </Router>
            </div>
        );
    }
}
 
export default Home;