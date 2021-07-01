import React from 'react';
import ReactDOM from 'react-dom';
import './assets/main.css';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Home from './components/Home'


ReactDOM.render(
    <Router>
        <Home />
    </Router>,
    document.getElementById('root')
);
