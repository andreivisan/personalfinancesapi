import React from 'react';
import {
    Route,
    Redirect
} from "react-router-dom";
import { ACCESS_TOKEN } from '../../constants';

const PrivateRoute = ({ component: Component, loggedIn, ...rest }) => (
    <Route {...rest} render={props => {
        if (!localStorage.getItem(ACCESS_TOKEN)) {
            return <Redirect to={{ pathname: '/login' }} />
        }

        return <Component {...props} />
    }} />
)

export default PrivateRoute