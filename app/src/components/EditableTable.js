import React, { Component } from 'react'
import MaterialTable from 'material-table'
import axios from 'axios';

import { ACCESS_TOKEN } from '../constants';

class EditableTable extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            columns: [
                {
                    title: 'Date',
                    field: 'transactionDate'
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
            data: this.props.csvEntities
        }
    }

    addExpenseToDB = (csvEntity) => {
        const jwtToken = localStorage.getItem(ACCESS_TOKEN);
        return axios.post("api/v1/expenses/save", csvEntity, {
            headers: {
                'Authorization': jwtToken
            }
        });
    }

    render() {
        return (
            <MaterialTable 
                title="Edit Transactions"
                data={this.state.data}
                columns={this.state.columns}
                options={{
                    actionsColumnIndex: -1
                }}
                editable={{
                    onRowDelete: selectedRow => new Promise((resolve, reject) => {
                        const index = selectedRow.tableData.id;
                        const updatedRows = [...this.state.data];
                        updatedRows.splice(index, 1);
                        this.setState({
                            data: updatedRows
                        })
                        resolve()
                    }),
                    onRowUpdate: (updatedRow, oldRow) => new Promise((resolve, reject) => {
                        const index = oldRow.tableData.id
                        const updatedRows = [...this.state.data]
                        updatedRows[index] = updatedRow
                        this.addExpenseToDB(updatedRow)
                            .then((response) => {
                                this.setState({
                                    data: updatedRows
                                })
                                if (response && response.status === 200) {
                                    resolve();
                                }
                            })
                            .catch((error) => {
                                console.log(error);
                                reject();
                            });
                    })
                }}
            />
        )
    }
}
 
export default EditableTable;

