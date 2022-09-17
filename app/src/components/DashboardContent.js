import React, { Component } from 'react'
import {ACCESS_TOKEN} from "../constants";
import axios from "axios";
import LineChart from "./LineChart";

class DashboardContent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            test: []
        }

        this.fetchTotals = this.fetchTotals.bind(this);
        this.showLineChart = this.showLineChart.bind(this);
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
                this.setState({ test: response.data });
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    showLineChart() {
        const allMonths = this.state.test;
        if (allMonths.length > 0) {
            const chartData = {
                labels: allMonths.map((data) => data.month),
                datasets: [
                    {
                        label: "Monthly Expenses for Groceries",
                        data: allMonths.map((data) => data.amount),
                        backgroundColor: "#111827",
                        borderColor: "#111827",
                        borderWidth: 2,
                    }
                ]
            }

            console.log(chartData)

            return (
                <LineChart chartData={chartData} />
            )
        } else {
            return (
                <></>
            )
        }
    }

    render() {
        return (
            <div className="flex-1 p-10 text-2xl font-bold bg-gray-100">
                {this.showLineChart()}
            </div>
        );
    }
}

export default DashboardContent
