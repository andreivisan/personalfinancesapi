import React, { Component } from 'react'
import {ACCESS_TOKEN} from "../constants";
import axios from "axios";
import LineChart from "./LineChart";
import PieChart from "./PieChart";

class DashboardContent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            totalExpensesForCategoryGroupByMonth: [],
            top7ExpenseCategories: []
        }

        this.fetchTotals = this.fetchTotals.bind(this);
        this.fetchExpenseCategories = this.fetchExpenseCategories.bind(this);
        this.showLineChart = this.showLineChart.bind(this);
    }

    componentDidMount() {
        this.fetchTotals();
        this.fetchExpenseCategories();
    }

    fetchTotals() {
        const jwtToken = localStorage.getItem(ACCESS_TOKEN);
        axios.get("api/v1/expenses/totals/Groceries", {
            headers: {
                'Authorization': jwtToken
            }
        })
            .then(response => {
                this.setState({ totalExpensesForCategoryGroupByMonth: response.data });
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    fetchExpenseCategories() {
        const jwtToken = localStorage.getItem(ACCESS_TOKEN);
        axios.get(`api/v1/categories/totalMonthlyAmountPerCategory/2022/6`, {
            headers: {
                'Authorization': jwtToken
            }
        })
            .then(response => {
                this.setState({ top7ExpenseCategories: response.data });
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    showLineChart() {
        const allMonths = this.state.totalExpensesForCategoryGroupByMonth;
        const allCategories = this.state.top7ExpenseCategories;
        if (allMonths.length > 0 && allCategories.length > 0) {
            const chartData = {
                labels: allMonths.map((data) => data.expenseDate.trim()),
                datasets: [
                    {
                        label: "2022",
                        data: allMonths.map((data) => data.total),
                        borderColor: "#111827",
                        borderWidth: 2,
                    }
                ]
            }

            const chartOptions = {
                legend: {
                    position: 'bottom'
                },
                title: {
                    position: 'bottom'
                },
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                responsive: true,
            }

            const pieData = {
                labels: allCategories.map((data) => data.category),
                datasets: [
                    {
                        label: "2022",
                        data: allCategories.map((data) => data.total),
                        backgroundColor: [
                            "#86efac",
                            "#6ee7b7",
                            "#0c4a6e",
                            "#fafafa",
                            "#a5b4fc",
                            "#0e7490",
                            "#34d399",
                        ],
                        borderColor: "#111827",
                        borderWidth: 1,
                    }
                ]
            }

            const pieOptions = {
                responsive: true,
            }

            return (

                <div className="flex flex-wrap">
                    <div className="w-full xl:w-6/12 px-4">
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
                                <div className="relative h-700-px">
                                    <LineChart chartData={chartData} options={chartOptions}/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="w-full xl:w-6/12 px-4">
                        <div
                            className="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded bg-blueGray-700">
                            <div className="rounded-t mb-0 px-4 py-3 bg-transparent">
                                <div className="flex flex-wrap items-center">
                                    <div className="relative w-full max-w-full flex-grow flex-1"><h6
                                        className="uppercase text-blueGray-400 mb-1 text-xs font-semibold">Savings</h6>
                                        <h2 className="text-blueGray-700 text-xl font-semibold">Categories To Save</h2></div>
                                </div>
                            </div>
                            <div className="p-4 flex-auto">
                                <div className="relative h-700-px">
                                    <div className="bg-white">
                                        <div className="grid grid-cols-3 gap-4 text-base">
                                            {allCategories.map(record => {
                                                return (
                                                    <>
                                                        <div className="col-span-2 text-gray-500"
                                                             key={record.category}>{record.category}</div>
                                                        <div>{record.total.toFixed(2)} <span className="text-gray-500">&euro;</span>
                                                        </div>
                                                    </>
                                                );
                                            })}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="w-full xl:w-6/12 px-4">
                        <div className="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-lg rounded">
                            <div className="rounded-t mb-0 px-4 py-3 bg-transparent">
                                <div className="flex flex-wrap items-center">
                                    <div className="relative w-full max-w-full flex-grow flex-1"><h6
                                        className="uppercase text-blueGray-400 mb-1 text-xs font-semibold">Top Expenses</h6>
                                        <h2 className="text-blueGray-700 text-xl font-semibold">August</h2></div>
                                </div>
                            </div>
                            <div className="p-4 flex-auto">
                                <div className="relative h-700-px">
                                    <PieChart chartData={pieData} options={pieOptions}/>
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
