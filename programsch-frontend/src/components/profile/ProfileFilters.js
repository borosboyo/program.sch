import React from "react";
import {Button} from "reactstrap";
import {Link} from "react-router-dom";
import './ProfileFilter.css';

export class ProfileFilters extends React.Component {

    constructor(props) {
        super(props);
        this.state = {filtersEnabled: {}};
        this.handleGetFilterState = this.handleGetFilterState.bind(this);
        this.handleEnableFilters = this.handleEnableFilters.bind(this);
        this.handleDisableFilters = this.handleDisableFilters.bind(this);
    }

    componentDidMount() {
        this.handleGetFilterState();
    }

    reloadPage() {
        const reloadCount = sessionStorage.getItem('reloadCount');
        if (reloadCount < 2) {
            sessionStorage.setItem('reloadCount', String(reloadCount + 1));
            window.location.reload();
        } else {
            sessionStorage.removeItem('reloadCount');
        }
    }

    handleGetFilterState() {
        fetch(`http://localhost:8080/api/filter/filtersEnabled`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({filter: data}));
    }

     handleEnableFilters() {
         fetch('http://localhost:8080/api/filter/enableFilters', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(data => console.log("enable"));
         this.handleGetFilterState();
         this.reloadPage();
    }

     handleDisableFilters() {
         fetch('http://localhost:8080/api/filter/disableFilters', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(data => console.log("disable"));
         this.handleGetFilterState();
         this.reloadPage();
    }

    //isEmptyObject(obj) {
    //  return !Object.keys(obj).length;
    //}

    render() {
        const filter = this.state.filter;
        if (filter) {
            return (
                <div className="col-md-6">
                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h3 className="panel-title">M??veletek</h3>
                        </div>
                        <div className="list-group align-items-center">
                            <Button tag={Link} to={"/filters"}
                               className="list-group-item list-group-item-info">Sz??r??k be??ll??t??sa</Button>
                            <Button onClick={this.handleDisableFilters}
                               className="list-group-item list-group-item-danger">Programok sz??r??s??nek
                                kikapcsol??sa</Button>
                        </div>
                    </div>
                </div>
            );
        } else {
            return (
                <div className="col-md-6">
                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h3 className="panel-title">M??veletek</h3>
                        </div>
                        <div className="list-group  align-items-center">
                            <Button className="list-group-item list-group-item-success" onClick={this.handleEnableFilters}>
                                Programok sz??r??s??nek bekapcsol??sa
                            </Button>
                        </div>
                    </div>
                </div>
            );
        }
    }
}
