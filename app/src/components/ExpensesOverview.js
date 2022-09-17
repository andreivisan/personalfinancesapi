import axios from 'axios';
import React, { Component } from 'react';

import DataTable from './DataTable';

import { ACCESS_TOKEN } from '../constants';

const months = [
    {
        label: "January",
        value: "1"
    },
    {
        label: "February",
        value: "2"
    },
    {
        label: "March",
        value: "3"
    },
    {
        label: "April",
        value: "4"
    },
    {
        label: "May",
        value: "5"
    },
    {
        label: "June",
        value: "6"
    },
    {
        label: "July",
        value: "7"
    },
    {
        label: "August",
        value: "8"
    },
    {
        label: "September",
        value: "9"
    },
    {
        label: "October",
        value: "10"
    },
    {
        label: "November",
        value: "11"
    },
    {
        label: "December",
        value: "12"
    },
]

class ExpensesOverview extends Component {
    constructor(props) {
        super(props);
        this.state = {
            month: '',
            year: '',
            totalMonthlyAmountPerCategory: []
        }

        this.handleYearChange = this.handleYearChange.bind(this);
        this.handleMonthChange = this.handleMonthChange.bind(this);
        this.showCategories = this.showCategories.bind(this);
        this.fetchCategories = this.fetchCategories.bind(this);
    }

    handleYearChange(event) {
        this.setState({
            year: event.target.value
        })
    }

    handleMonthChange(event) {
        this.setState({
            month: event.target.value
        })
    }

    fetchCategories() {
        const jwtToken = localStorage.getItem(ACCESS_TOKEN);
        axios.get(`api/v1/categories/totalMonthlyAmountPerCategory/${this.state.year}/${this.state.month}`, {
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

        return (
            <div className="mt-20">
                {totalAmountPerCategory.length > 0 &&
                    <div className="bg-white p-6 rounded-lg shadow-lg">
                            <h2 className="text-2xl font-bold mb-2 text-gray-800">Total expenses / category for selected month</h2>
                            <div className="grid grid-cols-3 gap-4 text-base mt-10">
                                {totalAmountPerCategory.map(record => {
                                    return (
                                        <>
                                            <div className="col-span-2 text-gray-500"
                                                 key={record.category}>{record.category}</div>
                                            <div>{record.totalAmount} <span className="text-gray-500">&euro;</span>
                                            </div>
                                        </>
                                    );
                                })}
                            </div>
                    </div>
                }
            </div>
        )
    }

    render() {
        return (
            <div class="flex-1 p-10 text-2xl font-bold bg-gray-100">
                <div class="text-2xl">EXPENSES OVERVIEW</div>

                <div class="relative inline-flex mt-20 mr-10">
                    <svg class="w-2 h-2 absolute top-0 right-0 m-4 pointer-events-none" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 412 232"><path d="M206 171.144L42.678 7.822c-9.763-9.763-25.592-9.763-35.355 0-9.763 9.764-9.763 25.592 0 35.355l181 181c4.88 4.882 11.279 7.323 17.677 7.323s12.796-2.441 17.678-7.322l181-181c9.763-9.764 9.763-25.592 0-35.355-9.763-9.763-25.592-9.763-35.355 0L206 171.144z" fill="#648299" fill-rule="nonzero"/></svg>
                    <select
                        value={this.state.year}
                        onChange={this.handleYearChange}
                        class="border border-gray-300 rounded-full text-gray-800 h-10 pl-5 pr-10 bg-white hover:border-gray-400 focus:outline-none appearance-none">
                        <option>Select Year</option>
                        <option value="2022">2022</option>
                    </select>
                </div>

                <div class="relative inline-flex">
                    <svg class="w-2 h-2 absolute top-0 right-0 m-4 pointer-events-none" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 412 232"><path d="M206 171.144L42.678 7.822c-9.763-9.763-25.592-9.763-35.355 0-9.763 9.764-9.763 25.592 0 35.355l181 181c4.88 4.882 11.279 7.323 17.677 7.323s12.796-2.441 17.678-7.322l181-181c9.763-9.764 9.763-25.592 0-35.355-9.763-9.763-25.592-9.763-35.355 0L206 171.144z" fill="#648299" fill-rule="nonzero"/></svg>
                    <select
                        value={this.state.month}
                        onChange={this.handleMonthChange}
                        class="border border-gray-300 rounded-full text-gray-800 h-10 pl-5 pr-10 bg-white hover:border-gray-400 focus:outline-none appearance-none">
                        <option>Select Month</option>
                        {months.map((month) => (
                            <option value={month.value}>{month.label}</option>
                        ))}
                    </select>
                </div>

                <div className="ml-10 relative inline-flex">
                    <button
                        className="border border-gray-300 rounded-full text-gray-800 h-10 pl-5 pr-10 bg-white hover:border-gray-400 focus:outline-none appearance-none"
                        onClick={this.fetchCategories}>
                        Fetch Data
                    </button>
                </div>

                {this.showCategories()}

                <div id="dataTable" class="text-sm text-gray-900 mt-20">
                    <DataTable onCategoryDelete={this.fetchCategories}/>
                </div>
            </div>
        );
    }
}

export default ExpensesOverview;