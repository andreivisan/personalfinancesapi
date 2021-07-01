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
        axios.get('api/v1/expenses/toTransactions', {
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
                            this.setState({
                                data: updatedRows
                            })
                            resolve()
                        }, 2000)
                    })
                }}
            />
        );
    }
}
 
export default DataTable;