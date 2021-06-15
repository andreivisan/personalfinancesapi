import React, { Component } from 'react'

class EditableTable extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() {
        if(this.props.csvEntities) {
            const csvEntities = this.props.csvEntities;
            return (
                <div>
                    {csvEntities.map(csvEntity => (
                        <div class="flex mb-4">
                            <div class="w-1/5 bg-gray-500 h-12">{csvEntity.transactionDate}</div>
                            <div class="w-1/5 bg-gray-400 h-12">{csvEntity.description}</div>
                            <div class="w-1/5 bg-gray-500 h-12">{csvEntity.amount}</div>
                            <div class="w-1/5 bg-gray-400 h-12">{csvEntity.paymentSystem}</div>
                            <div class="w-1/5 bg-gray-500 h-12"></div>
                        </div>
                    ))}
                </div>
            );
        } else {
            return (
                <div></div>
            );
        }
    }
}
 
export default EditableTable;

