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
            ]
        }
    }

    render() {
        // if(this.props.csvEntities) {
        //     const csvEntities = this.props.csvEntities;
        //     return (
        //         <div>
        //             {csvEntities.map(csvEntity => (
        //                 <div class="flex mb-4">
        //                     <div class="w-1/5 bg-gray-500 h-12">{csvEntity.transactionDate}</div>
        //                     <div class="w-1/5 bg-gray-400 h-12">{csvEntity.description}</div>
        //                     <div class="w-1/5 bg-gray-500 h-12">{csvEntity.amount}</div>
        //                     <div class="w-1/5 bg-gray-400 h-12">{csvEntity.paymentSystem}</div>
        //                     <div class="w-1/5 bg-gray-500 h-12"></div>
        //                 </div>
        //             ))}
        //         </div>
        //     );
        // } else {
        //     return (
        //         <div></div>
        //     );
        // }
        return (
            <MaterialTable title="Edit Expenses"
                data={this.props.csvEntities}
                columns={this.state.columns}
            />
        )
    }
}
 
export default EditableTable;

