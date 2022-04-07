import React from 'react';
import AppNavbar from "./AppNavbar";

export class EditFilters extends React.Component {

    constructor(props) {
        super(props);
        this.state = {resorts: [], circleList: [], userFilter: {}};
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/resort')
            .then(response => response.json())
            .then(data => this.setState({resorts: data}));

        fetch('http://localhost:8080/api/circle')
            .then(response => response.json())
            .then(data => this.setState({circleList: data}));
    }

    handleCircles(resort) {
        this.state.circleList.map(circle => {
            if (circle.resort.name === resort.name) {
               return <a href="#" data-toggle="filter-toggle" data-circle="46"
                         className="list-group-item list-group-item-danger">
                   {circle.displayName}
               </a>
            }
        });
        return <p>fasz</p>
    }

    render() {

        const resortList = this.state.resorts.map(resort => {
            return <div className="col-md-4" key={resort.name}>
                <div className="list-group">
                    <li className="list-group-item">
                        <h4 className="list-group-item-heading">
                            <b>{resort.name}</b>
                        </h4>
                    </li>
                    {
                        this.state.circleList.map(circle => {
                            if (circle.resort.name === resort.name) {
                                return <a key={circle.displayName} href="#" data-toggle="filter-toggle" data-circle="46"
                                          className="list-group-item list-group-item-danger">
                                    {circle.displayName}
                                </a>
                            }
                        })
                    }
                </div>
            </div>
        });

        return (
            <div>
                <AppNavbar/>
                <div className="container">
                    <div className="row">
                        {resortList}
                    </div>
                </div>
            </div>
        );
    }

}
