import React, {useEffect, useState} from 'react';
import AppNavbar from "../banner/AppNavbar";
import './EditFilter.css';

export function EditFilters() {

    const [resorts, setResorts] = useState([]);
    const [circleList, setCircleList] = useState([]);
    const [userFilters, setUserFilters] = useState([]);

    useEffect(() => {
        fetchResorts();
        fetchCircles();
        fetchUserFilters();
    });

    const fetchCircles = () => {
        fetch('http://localhost:8080/api/circle')
            .then(response => response.json())
            .then(data => setCircleList(data));
    }

    const fetchResorts = () => {
        fetch('http://localhost:8080/api/resort')
            .then(response => response.json())
            .then(data => setResorts(data));
    }

    const fetchUserFilters = () => {
        fetch('http://localhost:8080/api/filter')
            .then(response => response.json())
            .then(data => setUserFilters(data));
    }

    const handleClickOnCircle = (circle) => {

        if(userFilters.filteredCircles.includes(circle.displayName)){
            userFilters.filteredCircles.splice(userFilters.filteredCircles.indexOf(circle.displayName), 1);
        } else {
            userFilters.filteredCircles.push(circle.displayName);
        }

        handleUserFiltersChange(userFilters);
    }

    const handleUserFiltersChange = (userFilters) => {
        fetch('http://localhost:8080/api/filter/', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userFilters),
        }).then(r => r.json())
    }

    const handleCircleState = (circleName) => {
        if(userFilters.filteredCircles !== undefined) {
            if(userFilters.filteredCircles.includes(circleName)) {
                return "list-group-item list-group-item-danger";
            } else {
                return "list-group-item list-group-item-success";
            }
        }
    }

    const render = () => {
        const resortList = resorts.map(resort => {
            return <div className="col-md-4" id="resortCard" key={resort.name}>
                <div className="list-group">
                    <li className="list-group-item">
                        <h4 className="list-group-item-heading">
                            <b>{resort.name}</b>
                        </h4>
                    </li>
                    {
                        circleList.map(circle => {
                            if (circle.resort.name === resort.name) {
                                return <a onClick={() => handleClickOnCircle(circle)} key={circle.displayName} href="#"
                                          className={handleCircleState(circle.displayName)}>
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

    return render();
}
