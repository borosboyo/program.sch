import React from "react";
import {Button} from "reactstrap";
import {Link} from "react-router-dom";

export class ProfileFilters extends React.Component {

    constructor(props) {
        super(props);
        this.state = {filtersEnabled: {}};
    }

    componentDidMount() {
        this.handleGetFilters();
    }

    handleGetFilters() {
        fetch(`http://localhost:8080/getFiltersEnabled`, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({filter: data}));
    }

     handleEnableFilters() {
         fetch('http://localhost:8080/enableFilters', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(data => console.log("enable"));
    }

     handleDisableFilters() {
         fetch('http://localhost:8080/disableFilters', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(data => console.log("disable"));
    }

    //isEmptyObject(obj) {
    //  return !Object.keys(obj).length;
    //}

    render() {
        const filter = this.state.filter;
        if (!filter) {
            return (
                <div className="col-md-6">
                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h3 className="panel-title">Műveletek</h3>
                        </div>
                        <div className="list-group">
                            <Button tag={Link} to={"/filters"}
                               className="list-group-item list-group-item-info">Szűrők beállítása</Button>
                            <Button onClick={this.handleDisableFilters}
                               className="list-group-item list-group-item-danger">Programok szűrésének
                                kikapcsolása</Button>
                        </div>
                    </div>
                </div>
            );
        } else {
            return (
                <div className="col-md-6">
                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h3 className="panel-title">Műveletek</h3>
                        </div>
                        <div className="list-group">
                            <Button className="list-group-item list-group-item-info" onClick={this.handleEnableFilters}>
                                Programok szűrésének bekapcsolása
                            </Button>
                        </div>
                    </div>
                </div>
            );
        }
    }
}
