import axios from 'axios';
import React, { Component } from 'react';

import DataTable from './DataTable';

import { ACCESS_TOKEN } from '../constants';

class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            totalMonthlyAmountPerCategory: []
        }

        this.showCategories = this.showCategories.bind(this);
        this.fetchCategories = this.fetchCategories.bind(this);
    }

    componentDidMount() {
        this.fetchCategories();
    }

    fetchCategories() {
        const jwtToken = localStorage.getItem(ACCESS_TOKEN);
        axios.get('api/v1/categories/totalMonthlyAmountPerCategory', {
            headers: {
                'Authorization': jwtToken
            }
        })
            .then(response => {
                this.setState({ totalMonthlyAmountPerCategory: response.data });
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    showCategories() {
        const totalAmountPerCategory = this.state.totalMonthlyAmountPerCategory;
        return Object.keys(totalAmountPerCategory).map(function(key) {
            return (
                <>
                    <div class="col-span-2 text-gray-500">{key}</div>
                    <div>{totalAmountPerCategory[key]} <span class="text-gray-500">&euro;</span></div>
                </>
            )
        })
    }

    render() {
        const totalMonthlyAmountPerCategory = this.state.totalMonthlyAmountPerCategory;

        return (
            <div class="flex-1 p-10 text-2xl font-bold bg-gray-100">
                <div class="text-2xl">EXPENSES OVERVIEW</div>

                <div class="mt-20">
                    <div class="bg-white p-6 rounded-lg shadow-lg">
                        <h2 class="text-2xl font-bold mb-2 text-gray-800">This month's expenses / category</h2>
                        <div class="grid grid-cols-3 gap-4 text-base mt-10">
                            {this.showCategories()}
                        </div>
                    </div>
                </div>

                <div id="dataTable" class="text-sm text-gray-900 mt-20">
                    <DataTable onCategoryDelete={this.fetchCategories}/>
                </div>
            </div>
        );
    }
}

export default Dashboard;