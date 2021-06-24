import React from 'react';
import {
    Route,
    Redirect
} from "react-router-dom";


const PrivateRoute = ({ component: Component, loggedIn, path, ...rest }) => {
    return (
        <Route
            path={path}
            {...rest}
            render={(props) => {
                return loggedIn ? (
                    <Component {...props} />
                ) : (
                    <Redirect
                        to={{
                            pathname: "/login",
                            state: {
                                prevLocation: path,
                                error: "You need to login first!",
                            },
                        }}
                    />
                );
            }}
        />
    );
};

export default PrivateRoute