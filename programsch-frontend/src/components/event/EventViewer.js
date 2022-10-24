import React, {useEffect, useState} from 'react';
import AppNavbar from "../banner/AppNavbar";
import GroupsIcon from '@mui/icons-material/Groups';
import './EventViewer.css';
import {Event} from "./Event";
import {useHistory} from "react-router-dom";

export function EventViewer(props) {

    const [currentEvent, setCurrentEvent] = useState({});
    const [currentCircle, setCurrentCircle] = useState({});
    const id = props.match.params.id;
    const history = useHistory();

    useEffect(() => {
        fetchEvent();
    }, []);

    const fetchEvent = () => {
        fetch(`http://localhost:8080/api/event/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then((data) => {
                setCurrentEvent(data);
                setCurrentCircle(data.circle);
            })
    }

    const handleDelete = () => {
        fetch(`http://localhost:8080/api/event/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
        })
        history.push("/");
    }

    const render = () => {
        return (
            <div>
                <AppNavbar/>
                <div className="container justify-content-center">
                    <div className="card" id="card">
                        <div className="card-header"><GroupsIcon/> {currentCircle.displayName}</div>
                        <img src={currentEvent.poster} className="card-img-top" alt="..."/>
                        <Event currentEvent={currentEvent} onClick={() => handleDelete()}/>
                    </div>
                </div>
            </div>
        );
    }

    return render();
}
