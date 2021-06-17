import axios from 'axios';
import React, { Component } from 'react';

import DataTable from './DataTable';

class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            totalMonthlyAmountPerCategory: []
        }
    }

    componentDidMount() {
        axios.get('api/v1/categories/totalMonthlyAmountPerCategory')
             .then(response => {
                 this.setState({ totalMonthlyAmountPerCategory: response.data });
             })
             .catch(function (error) {
                 console.log(error);
             })
    }

    categories() {
        return this.state.totalMonthlyAmountPerCategory.map(data => {
            return (
                <>
                    <div class="col-span-2 text-gray-500">{data.category}</div>
                    <div>{data.totalAmount} <span class="text-gray-500">&euro;</span></div>
                </>
            )
        })
    }

    getTransactions() {
        return <DataTable />
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
                            {this.categories()}
                        </div>
                    </div>
                </div>

                <div id="dataTable" class="text-sm text-gray-900 mt-20">
                    {this.getTransactions()}
                </div>
            </div>
        );
    }
}
 
export default Dashboard;