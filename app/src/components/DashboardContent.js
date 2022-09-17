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
                        label: "2022",
                        data: allMonths.map((data) => data.amount),
                        borderColor: "#111827",
                        borderWidth: 2,
                    }
                ]
            }

            const options = {
                legend: {
                    position: 'bottom'
                },
                title: {
                    position: 'bottom'
                }
            }

            console.log(chartData)

            return (
                <div className="flex flex-wrap">
                    <div className="w-full xl:w-6/12 mb-12 xl:mb-0 px-4">
                        <div
                            className="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded bg-blueGray-700">
                            <div className="rounded-t mb-0 px-4 py-3 bg-transparent">
                                <div className="flex flex-wrap items-center">
                                    <div className="relative w-full max-w-full flex-grow flex-1"><h6
                                        className="uppercase text-blueGray-400 mb-1 text-xs font-semibold">Groceries</h6>
                                        <h2 className="text-blueGray-700 text-xl font-semibold">Monthly Amount</h2></div>
                                </div>
                            </div>
                            <div className="p-4 flex-auto">
                                <div className="relative h-350-px">
                                    <LineChart chartData={chartData} options={options}/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="w-full xl:w-6/12 px-4">
                        <div className="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-lg rounded">
                            <div className="rounded-t mb-0 px-4 py-3 bg-transparent">
                                <div className="flex flex-wrap items-center">
                                    <div className="relative w-full max-w-full flex-grow flex-1"><h6
                                        className="uppercase text-blueGray-400 mb-1 text-xs font-semibold">Performance</h6>
                                        <h2 className="text-blueGray-700 text-xl font-semibold">Total orders</h2></div>
                                </div>
                            </div>
                            <div className="p-4 flex-auto">
                                <div className="relative h-350-px">
                                    <LineChart chartData={chartData} />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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
