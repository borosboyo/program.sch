import React from 'react';
import AppNavbar from "../banner/AppNavbar";
import './EditFilter.css';

export class EditFilters extends React.Component {

    constructor(props) {
        super(props);
        this.state = {resorts: [], circleList: [], userFilters: {}};
        this.handleClickOnCircle = this.handleClickOnCircle.bind(this);
        this.handleCircleState = this.handleCircleState.bind(this);
    }

    componentDidMount() {
        this.fetchResorts();
        this.fetchCircles();
        this.fetchUserFilters();
    }

    fetchCircles() {
        fetch('http://localhost:8080/api/circle')
            .then(response => response.json())
            .then(data => this.setState({circleList: data}));
    }

    fetchResorts() {
        fetch('http://localhost:8080/api/resort')
            .then(response => response.json())
            .then(data => this.setState({resorts: data}));
    }

    fetchUserFilters() {
        fetch('http://localhost:8080/api/filter')
            .then(response => response.json())
            .then(data => this.setState({userFilters: data}));
    }

    handleClickOnCircle(circle){
        let userFilters = this.state.userFilters;

        if(userFilters.filteredCircles.includes(circle.displayName)){
            userFilters.filteredCircles.splice(userFilters.filteredCircles.indexOf(circle.displayName), 1);
        } else {
            userFilters.filteredCircles.push(circle.displayName);
        }

        this.handleUserFiltersChange(userFilters);
    }

    handleUserFiltersChange(userFilters) {
        fetch('http://localhost:8080/api/filter/', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userFilters),
        });
    }

    handleCircleState(circleName) {
        if(this.state.userFilters.filteredCircles !== undefined) {
            if(this.state.userFilters.filteredCircles.includes(circleName)) {
                return "list-group-item list-group-item-danger";
            } else {
                return "list-group-item list-group-item-success";
            }
        }
    }

    render() {
        const resortList = this.state.resorts.map(resort => {
            return <div className="col-md-4" id="resortCard" key={resort.name}>
                <div className="list-group">
                    <li className="list-group-item">
                        <h4 className="list-group-item-heading">
                            <b>{resort.name}</b>
                        </h4>
                    </li>
                    {
                        // eslint-disable-next-line array-callback-return
                        this.state.circleList.map(circle => {
                            if (circle.resort.name === resort.name) {
                                // eslint-disable-next-line jsx-a11y/anchor-is-valid
                                return <a onClick={() => this.handleClickOnCircle(circle)} key={circle.displayName} href="#"
                                          className={this.handleCircleState(circle.displayName)}>
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
