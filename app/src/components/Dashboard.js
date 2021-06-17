import axios from 'axios';
import React, { Component } from 'react';

class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <div class="flex-1 p-10 text-2xl font-bold bg-gray-100">
                <div class="text-2xl">EXPENSES OVERVIEW</div>

                <div class="p-20">
                    <div class="bg-white p-6 rounded-lg shadow-lg">
                        <h2 class="text-2xl font-bold mb-2 text-gray-800">This month's expenses / category</h2>
                        <div class="grid grid-cols-3 gap-4 text-base">
                            <div class="col-span-2 text-gray-500">Groceries</div>
                            <div>5 <span class="text-gray-500">&euro;</span></div>
                        </div>
                    </div>
                </div>

                <div id="expensesTable" class="text-sm text-gray-900"></div>
            </div>
        );
    }
}
 
export default Dashboard;