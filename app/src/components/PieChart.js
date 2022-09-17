import {Component} from "react";
import { Pie } from "react-chartjs-2"
import { Chart as ChartJS } from "chart.js/auto"

class PieChart extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return <Pie data={this.props.chartData} options={this.props.options}/>
    }
}

export default PieChart;