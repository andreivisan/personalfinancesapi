import React, { Component } from 'react'
import MaterialTable from 'material-table'
import axios from 'axios';

import { ACCESS_TOKEN } from '../constants';

class DataTable extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            columns: [
                {
                    title: 'ID',
                    field: 'transactionId',
                    hidden: true
                },
                {
                    title: 'Date',
                    field: 'date'
                },
                {
                    title: 'Label',
                    field: 'description'
                },
                {
                    title: 'Amount',
                    field: 'amount'
                },
                {
                    title: 'Category',
                    field: 'category'
                },
                {
                    title: 'Payment System',
                    field: 'paymentSystem'
                }
            ],
            data: []
        }
    }

    componentDidMount() {
        const jwtToken = localStorage.getItem(ACCESS_TOKEN);
        axios.get('api/v1/expenses/transactions', {
            headers: {
                'Authorization': jwtToken
            }
        })
             .then(response => {
                this.setState({ data: response.data });
             })
             .catch(function (error) {
                console.log(error);
             })
    }

    deleteExpenseFromDB = (transactionId) => {
        const jwtToken = localStorage.getItem(ACCESS_TOKEN);
        return axios.delete(`api/v1/expenses/${transactionId}`, {
            headers: {
                'Authorization': jwtToken
            }
        });
    }

    render() { 
        return ( 
            <MaterialTable 
                title="Transactions"
                data={this.state.data}
                columns={this.state.columns}
                options={{
                    actionsColumnIndex: -1,
                    filtering: true
                }}
                editable={{
                    onRowDelete: selectedRow => new Promise((resolve, reject) => {
                        const index = selectedRow.tableData.id;
                        const updatedRows = [...this.state.data];
                        updatedRows.splice(index, 1);
                        setTimeout(() => {
                            this.deleteExpenseFromDB(selectedRow.transactionId)
                                .then((response) => {
                                    this.setState({
                                        data: updatedRows
                                    })
                                    if (response && response.status === 200) {
                                        resolve();
                                    }
                                    this.props.onCategoryDelete();
                                })
                                .catch((error) => {
                                    console.log(error);
                                    reject();
                                });
                        }, 2000)
                    })
                }}
            />
        );
    }
}
 
export default DataTable;