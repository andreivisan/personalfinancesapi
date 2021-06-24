import axios from 'axios';
import React, { Component } from 'react';

import EditableTable from './EditableTable';

class UploadExpenses extends Component {
    state = {
        selectedFile: null,
        csvEntities: null
    }

    onFileChange = event => {
        this.setState({ selectedFile: event.target.files[0] });
    }

    onFileUpload = () => {
        const formData = new FormData();

        formData.append(
            "expensesCsv",
            this.state.selectedFile
        );

        axios.post("api/v1/fileupload/", formData)
            .then((response) => {
                this.setState({ csvEntities: response.data })
            })
            .catch(function (error) {
                console.log(error);
            });
    };

    renderCSVEntities() {
        if (this.state.csvEntities) {
            return <EditableTable csvEntities={this.state.csvEntities} />
        } else {
            return <></>
        }
    }

    render() {
        return (
            <div class="flex-1 p-10 text-2xl font-bold bg-gray-100">
                <div class="text-2xl">UPLOAD EXPENSES</div>

                <div class="mt-12 flex justify-center px-6 pt-5 pb-6 border-2 border-gray-300 border-dashed rounded-md">
                    <div class="space-y-1 text-center">
                        <svg class="mx-auto h-12 w-12 text-gray-400" stroke="currentColor" fill="none" viewBox="0 0 24 24" aria-hidden="true">
                            <path d="M6 12h10v1h-10v-1zm7.816-3h-7.816v1h9.047c-.45-.283-.863-.618-1.231-1zm5.184 1.975v2.569c0 4.106-6 2.456-6 2.456s1.518 6-2.638 6h-7.362v-20h9.5c.312-.749.763-1.424 1.316-2h-12.816v24h10.189c3.163 0 9.811-7.223 9.811-9.614v-3.886c-.623.26-1.297.421-2 .475zm-13-3.975h6.5c-.134-.32-.237-.656-.319-1h-6.181v1zm17-2.5c0 2.485-2.017 4.5-4.5 4.5s-4.5-2.015-4.5-4.5 2.017-4.5 4.5-4.5 4.5 2.015 4.5 4.5zm-2-.5h-2v-2h-1v2h-2v1h2v2h1v-2h2v-1z" />
                        </svg>
                        <div class="flex text-sm text-gray-600">
                            <label class="mt-2 relative cursor-pointe rounded-md font-medium text-indigo-600 hover:text-indigo-500 focus-within:outline-none focus-within:ring-2 focus-within:ring-offset-2 focus-within:ring-indigo-500">
                                <span>Upload a file</span>
                                <input id="file-upload" name="file-upload" type="file" class="sr-only" onChange={this.onFileChange} />
                            </label>
                            <p class="mt-2 pl-1">or drag and drop</p>
                        </div>
                    </div>
                </div>

                <div class="px-4 py-3 text-right sm:px-1">
                    <button class="inline-flex justify-center hover:bg-white text-sm font-medium py-2 px-4 border border-gray-400 rounded shadow" onClick={this.onFileUpload}>
                        Upload
                    </button>
                </div>

                <div id="editableTable" class="text-sm text-gray-900">
                    {this.renderCSVEntities()}
                </div>
            </div>
        );
    }
}

export default UploadExpenses;