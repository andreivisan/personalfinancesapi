import React, { Component } from 'react'
import { Link } from "react-router-dom";
import axios from 'axios';
import { ACCESS_TOKEN } from '../constants';

class LoginForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            usernameOrEmail: '',
            password: ''
        }

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        event.preventDefault();

        axios.post("api/v1/auth/signin", this.state)
            .then((response) => {
                localStorage.setItem(ACCESS_TOKEN, response.data.accessToken);
                console.log("test");
                this.props.history.push('/');
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    render() {
        return (
            <div class="flex flex-col h-screen bg-gray-100">
                <div class="grid place-items-center mx-2 my-20 sm:my-auto">
                    <div class="w-11/12 p-12 sm:w-8/12 md:w-6/12 lg:w-5/12 2xl:w-4/12 
                        px-6 py-10 sm:px-10 sm:py-6 
                        bg-white rounded-lg shadow-md lg:shadow-lg">

                        <h2 class="text-center font-semibold text-3xl lg:text-4xl text-gray-800">
                            Login
                        </h2>

                        <form class="mt-10" onSubmit={this.handleSubmit}>
                            <label for="usernameOrEmail" class="block text-xs font-semibold text-gray-600 uppercase">Username or E-mail</label>
                            <input id="usernameOrEmail" type="usernameOrEmail" name="usernameOrEmail" placeholder="username or e-mail" autocomplete="usernameOrEmail"
                                class="block w-full py-3 px-1 mt-2 
                                text-gray-800 appearance-none 
                                border-b-2 border-gray-100
                                focus:text-gray-500 focus:outline-none focus:border-gray-200"
                                required
                                value={this.state.usernameOrEmail}
                                onChange={this.handleInputChange} />

                            <label for="password" class="block mt-2 text-xs font-semibold text-gray-600 uppercase">Password</label>
                            <input id="password" type="password" name="password" placeholder="password" autocomplete="current-password"
                                class="block w-full py-3 px-1 mt-2 mb-4
                                text-gray-800 appearance-none 
                                border-b-2 border-gray-100
                                focus:text-gray-500 focus:outline-none focus:border-gray-200"
                                required
                                value={this.state.password}
                                onChange={this.handleInputChange} />

                            <button type="submit"
                                class="w-full py-3 mt-10 bg-gray-900 rounded-sm
                                font-medium text-white uppercase
                                focus:outline-none hover:bg-gray-700 hover:shadow-none">
                                Login
                            </button>

                            <div class="sm:flex sm:flex-wrap mt-8 sm:mb-4 text-sm text-center">
                                Not a user?
                                <p class="flex-2 underline ml-1">
                                    <Link to="/register"> Create a user </Link>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default LoginForm;