import React, {useEffect, useState} from "react";
import {Button} from "reactstrap";
import {Link} from "react-router-dom";
import './ProfileFilter.css';

export function ProfileFilters(){

    const [filtersEnabled, setFiltersEnabled] = useState({});

    useEffect(() => {
        handleGetFilterState();
    });

    const reloadPage = () => {
        const reloadCount = sessionStorage.getItem('reloadCount');
        if (reloadCount < 2) {
            sessionStorage.setItem('reloadCount', String(reloadCount + 1));
            window.location.reload();
        } else {
            sessionStorage.removeItem('reloadCount');
        }
    }

    const handleGetFilterState = () => {
        fetch(`http://localhost:8080/api/filter/filtersEnabled`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setFiltersEnabled(data));
    }

     const handleEnableFilters = () => {
         fetch('http://localhost:8080/api/filter/enableFilters', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(data => console.log("Enable"));
         handleGetFilterState();
         reloadPage();
    }

     const handleDisableFilters = () => {
         fetch('http://localhost:8080/api/filter/disableFilters', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(data => console.log("Disable"));
         handleGetFilterState();
         reloadPage();
    }

    const renderFilters = () => {
        if (filtersEnabled) {
            return (
                <div className="col-md-6">
                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h3 className="panel-title">Műveletek</h3>
                        </div>
                        <div className="list-group align-items-center">
                            <Button tag={Link} to={"/filters"}
                               className="list-group-item list-group-item-info">Szűrők beállítása</Button>
                            <Button onClick={handleDisableFilters}
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
                        <div className="list-group  align-items-center">
                            <Button className="list-group-item list-group-item-success" onClick={handleEnableFilters}>
                                Programok szűrésének bekapcsolása
                            </Button>
                        </div>
                    </div>
                </div>
            );
        }
    }

    return renderFilters();
}

export default ProfileFilters;
