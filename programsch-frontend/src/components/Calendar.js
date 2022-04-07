import React from 'react'
import '../css/Calendar.css'
import moment from "moment";

export default class Calendar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {startOfWeek: '', endOfWeek: '', week: {}};
    }

    componentDidMount() {
        this.resolveDates();
        this.fetchWeek();
    }

    fetchWeek() {
        const startOfWeek = this.state.startOfWeek;
        const endOfWeek = this.state.endOfWeek;
        fetch('http://localhost:8080/api/day/week', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(startOfWeek, endOfWeek)
        });
    }

    resolveDates() {
        this.setState({startOfWeek: moment().startOf('week').format('YYYY. MM. DD.')});
        this.setState({endOfWeek: moment().endOf('week').format('YYYY. MM. DD.')});
    }

    handleNextWeek() {

    }

    handlePrevWeek() {

    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row seven-cols justify-content-center">
                        <div className="col-md-1 card text-center">Col 1</div>
                        <div className="col-md-1 card text-center">Col 2</div>
                        <div className="col-md-1 card text-center">Col 3</div>
                        <div className="col-md-1 card text-center">Col 4</div>
                        <div className="col-md-1 card text-center">Col 5</div>
                        <div className="col-md-1 card text-center">Col 6</div>
                        <div className="col-md-1 card text-center">Col 7</div>
                    </div>
                    <div className="row seven-cols justify-content-center">
                        <div className="col-md-1 card text-center">Col 1</div>
                        <div className="col-md-1 card text-center">Col 2</div>
                        <div className="col-md-1 card text-center">Col 3</div>
                        <div className="col-md-1 card text-center">Col 4</div>
                        <div className="col-md-1 card text-center">Col 5</div>
                        <div className="col-md-1 card text-center">Col 6</div>
                        <div className="col-md-1 card text-center">Col 7</div>
                    </div>
                </div>
                <div className="d-flex justify-content-center">
                    <p>{this.state.startOfWeek} - {this.state.endOfWeek}</p>
                </div>
                <div className="button-box col-lg-12">
                    <a href="" className="btn btn-primary" role="button">
                        Előző hét
                    </a>
                    <a href="" className="btn btn-primary" role="button">
                        Következő hét
                    </a>
                </div>
            </div>
        );
    }
}



