import React, { Component } from 'react'
import MaterialTable from 'material-table'

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

    render() {
        return (
            <MaterialTable 
                title="Edit Expenses"
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
                        setTimeout(() => {
                            this.setState({
                                data: updatedRows
                            })
                            resolve()
                        }, 2000)
                    }),
                    onRowUpdate: (updatedRow, oldRow) => new Promise((resolve, reject) => {
                        const index = oldRow.tableData.id
                        const updatedRows = [...this.state.data]
                        updatedRows[index] = updatedRow
                        setTimeout(() => {
                            this.setState({
                                data: updatedRows
                            })
                            resolve()
                        }, 2000)
                    })
                }}
            />
        )
    }
}
 
export default EditableTable;

