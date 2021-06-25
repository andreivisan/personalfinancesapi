import React, { Component } from 'react'
import axios from 'axios';

class RegisterForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            username: '',
            email: '',
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

        axios.post("api/v1/auth/signup", this.state)
            .then((response) => {
                this.props.history.push("/login")
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
                            Register
                        </h2>

                        <form class="mt-10" onSubmit={this.handleSubmit}>
                        <label for="name" class="block text-xs font-semibold text-gray-600 uppercase">Name</label>
                            <input id="name" type="name" name="name" placeholder="name" autocomplete="name"
                                class="block w-full py-3 px-1 mt-2 
                                text-gray-800 appearance-none 
                                border-b-2 border-gray-100
                                focus:text-gray-500 focus:outline-none focus:border-gray-200"
                                required
                                value={this.state.name}
                                onChange={this.handleInputChange} />

                            <label for="username" class="block mt-2 text-xs font-semibold text-gray-600 uppercase">Username</label>
                            <input id="username" type="username" name="username" placeholder="username" autocomplete="username"
                                class="block w-full py-3 px-1 mt-2 
                                text-gray-800 appearance-none 
                                border-b-2 border-gray-100
                                focus:text-gray-500 focus:outline-none focus:border-gray-200"
                                required
                                value={this.state.username}
                                onChange={this.handleInputChange} />

                            <label for="email" class="block mt-2 text-xs font-semibold text-gray-600 uppercase">E-mail</label>
                            <input id="email" type="email" name="email" placeholder="e-mail address" autocomplete="email"
                                class="block w-full py-3 px-1 mt-2 
                                text-gray-800 appearance-none 
                                border-b-2 border-gray-100
                                focus:text-gray-500 focus:outline-none focus:border-gray-200"
                                required
                                value={this.state.email}
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
                                Register
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default RegisterForm;