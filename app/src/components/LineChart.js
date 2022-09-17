import {Component} from "react";
import { Line } from "react-chartjs-2"
import { Chart as ChartJS } from "chart.js/auto"

class LineChart extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return <Line data={this.props.chartData} options={this.props.options}/>
    }
}

export default LineChart;