import React, { Component } from 'react'
import {ACCESS_TOKEN} from "../constants";
import axios from "axios";
import LineChart from "./LineChart";

class DashboardContent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            totalAmountPerCategoryGroupByMonth: [],
            chartData: {}
        }

        this.fetchTotals = this.fetchTotals.bind(this);
    }

    componentDidMount() {
        this.fetchTotals();
    }

    fetchTotals() {
        const jwtToken = localStorage.getItem(ACCESS_TOKEN);
        axios.get("api/v1/expenses/totals/Groceries", {
            headers: {
                'Authorization': jwtToken
            }
        })
            .then(response => {
                this.setState({ totalAmountPerCategoryGroupByMonth: response.data });

            })
            .catch(function (error) {
                console.log(error);
            })
    }

    render() {
        return (
            <div className="flex-1 p-10 text-2xl font-bold bg-gray-100">
                <LineChart chartData={this.state.totalAmountPerCategoryGroupByMonth} />
            </div>
        );
    }
}

export default DashboardContent
