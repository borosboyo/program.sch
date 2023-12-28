import React, {useEffect, useState} from 'react';
import AppNavbar from "../banner/AppNavbar";
import './EditFilter.css';
import {Alert, AlertDescription, AlertIcon, ScaleFade, useDisclosure} from "@chakra-ui/react";

export function EditFilters() {

    const [resorts, setResorts] = useState([]);
    const [circleList, setCircleList] = useState([]);
    const [userFilters, setUserFilters] = useState([]);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const {isOpen, onToggle} = useDisclosure()

    useEffect(() => {
        onToggle();
        fetchResorts();
        fetchCircles();
        fetchUserFilters();
        handleGetLoginState();
    },[]);  // eslint-disable-line react-hooks/exhaustive-deps

    const handleGetLoginState = () => {
        fetch(`http://localhost:8080/api/isLoggedIn`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setIsLoggedIn(data));
    }

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
                                //eslint-disable-next-line jsx-a11y/anchor-is-valid
                                return <a onClick={() => handleClickOnCircle(circle)} key={circle.displayName} href="#"
                                          className={handleCircleState(circle.displayName)}>
                                    {circle.displayName}
                                </a>
                            }
                            return <></>
                        })
                    }
                </div>
            </div>
        });

        if(!isLoggedIn) {
            return (
                <div>
                    <AppNavbar/>
                    <ScaleFade initialScale={0.5} in={isOpen}>
                        <Alert status='error'>
                            <AlertIcon/>
                            <AlertDescription>A szűrőid szerkesztéséhez kérlek jelentkezz be.</AlertDescription>
                        </Alert>
                    </ScaleFade>
                </div>
            )
        } else {
            return (
                <div>
                    <AppNavbar/>
                    <div className="container" style={{marginTop: '35px'}}>
                        <div className="row">
                            {resortList}
                        </div>
                    </div>
                </div>
            );
        }
    }

    return render();
}
