import React from 'react';
import {
    Route,
    Redirect
} from "react-router-dom";


// const PrivateRoute = ({ component: Component, loggedIn, path, ...rest }) => {
//     return (
//         <Route
//             path={path}
//             {...rest}
//             render={(props) => {
//                 console.log(loggedIn);
//                 if (loggedIn) {
//                     return <Component {...rest} {...props} />
//                 }

//                 return <Redirect
//                             to={{
//                                 pathname: "/login",
//                                 state: {
//                                     prevLocation: path,
//                                     error: "You need to login first!",
//                                 },
//                             }}
//                         />
//             }}
//         />
//     );
// };

// export default PrivateRoute

const PrivateRoute = ({ component: Component, loggedIn, ...rest }) => (
    <Route {...rest} render={props => {
        if (!loggedIn) {
            return <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
        }

        return <Component {...props} />
    }} />
)

export default PrivateRoute