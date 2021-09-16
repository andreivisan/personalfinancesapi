import axios from 'axios';
import React, { Component } from 'react';

import DataTable from './DataTable';

import { ACCESS_TOKEN } from '../constants';

class ExpensesOverview extends Component {
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

                <div class="relative inline-flex mt-20 mr-10">
                    <svg class="w-2 h-2 absolute top-0 right-0 m-4 pointer-events-none" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 412 232"><path d="M206 171.144L42.678 7.822c-9.763-9.763-25.592-9.763-35.355 0-9.763 9.764-9.763 25.592 0 35.355l181 181c4.88 4.882 11.279 7.323 17.677 7.323s12.796-2.441 17.678-7.322l181-181c9.763-9.764 9.763-25.592 0-35.355-9.763-9.763-25.592-9.763-35.355 0L206 171.144z" fill="#648299" fill-rule="nonzero"/></svg>
                    <select class="border border-gray-300 rounded-full text-gray-800 h-10 pl-5 pr-10 bg-white hover:border-gray-400 focus:outline-none appearance-none">
                        <option>Select Year</option>
                        <option value="2021">2021</option>
                    </select>
                </div>

                <div class="relative inline-flex">
                    <svg class="w-2 h-2 absolute top-0 right-0 m-4 pointer-events-none" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 412 232"><path d="M206 171.144L42.678 7.822c-9.763-9.763-25.592-9.763-35.355 0-9.763 9.764-9.763 25.592 0 35.355l181 181c4.88 4.882 11.279 7.323 17.677 7.323s12.796-2.441 17.678-7.322l181-181c9.763-9.764 9.763-25.592 0-35.355-9.763-9.763-25.592-9.763-35.355 0L206 171.144z" fill="#648299" fill-rule="nonzero"/></svg>
                    <select class="border border-gray-300 rounded-full text-gray-800 h-10 pl-5 pr-10 bg-white hover:border-gray-400 focus:outline-none appearance-none">
                        <option>Select Month</option>
                        <option value="1">January</option>
                        <option value="2">February</option>
                        <option value="3">March</option>
                        <option value="4">April</option>
                        <option value="5">May</option>
                        <option value="6">June</option>
                        <option value="7">July</option>
                        <option value="8">August</option>
                        <option value="9">September</option>
                        <option value="10">October</option>
                        <option value="11">November</option>
                        <option value="12">December</option>
                    </select>
                </div>

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

export default ExpensesOverview;