import './EventViewer.css';
import React, {useEffect, useState} from 'react';
import AppNavbar from "../banner/AppNavbar";
import GroupsIcon from "@mui/icons-material/Groups";
import SentimentDissatisfiedIcon from '@mui/icons-material/SentimentDissatisfied';
import moment from "moment";
import {Event} from "./Event";
import {useHistory} from "react-router-dom";


export function DayViewer(props) {

    const [dayEvents, setDayEvents] = useState([]);
    const [loading, setLoading] = useState(true);
    const history = useHistory();
    const id = props.match.params.id;

    useEffect(() => {
        setLoading(true);
        fetchDay();
    }, []);


    const fetchDay = (props) => {
        fetch(`http://localhost:8080/api/event/day?date=${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setDayEvents(data));
        setLoading(false);
    }

    const handleDelete = (event) => {
        fetch(`http://localhost:8080/api/event/${event.id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
        })
        history.push("/");
    }

    const renderEvents = (props) => {
        const currentDayEvents = dayEvents.map(currentEvent =>
            <div className="container justify-content-center" key={currentEvent.name}>
                <div className="card" id="card">
                    <div className="card-header"><GroupsIcon/> {currentEvent.circle.displayName}</div>
                    <img src={currentEvent.poster} className="card-img-top" alt="..."/>
                    <Event currentEvent={currentEvent} onClick={() => handleDelete()}/>
                </div>
            </div>
        );

        if (loading === true) {
            return <div>Loading...</div>
        }

        if (dayEvents.length === 0) {
            return (
                <div>
                    <AppNavbar/>
                    <div className="container justify-content-center">
                        <div className="card" id="card">
                            <div className="card-header">Hoppá!</div>
                            <div className="card-body">
                                <h5 className="card-title text-center"> A mai napra nem találhatóak
                                    események! <SentimentDissatisfiedIcon/></h5>
                            </div>
                        </div>
                    </div>
                </div>
            )
        } else {
            return (
                <div>
                    <AppNavbar/>
                    <div className="container justify-content-center">
                        <div className="card" id="card">
                            <div className="card-header">{moment(id).format("MM. DD.")}</div>
                        </div>
                    </div>
                    {currentDayEvents}
                </div>
            );
        }
    }
    return renderEvents(props);
}
